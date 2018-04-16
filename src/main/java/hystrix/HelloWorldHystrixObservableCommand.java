/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author fenghao.xing
 * @version : HelloWorldHystrixObservableCommand.java, v 0.1 2018-04-16 20:57 fenhao.xing Exp $
 */
public class HelloWorldHystrixObservableCommand extends HystrixObservableCommand<String> {

    private final String name;

    protected HelloWorldHystrixObservableCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        System.out.println("in construct! thread:" + Thread.currentThread().getName());
        return (Observable<String>) Observable.create(new Observable.OnSubscribe<String>() {
            //            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    System.out.println("in call of construct! thread:"
                            + Thread.currentThread().getName());
                    if (!observer.isUnsubscribed()) {
                        observer.onError(getExecutionException()); // ֱ�����쳣�˳�����������ִ��
                        observer.onNext("Hello1" + " thread:" + Thread.currentThread().getName());
                        observer.onNext("Hello2" + " thread:" + Thread.currentThread().getName());
                        observer.onNext(name + " thread:" + Thread.currentThread().getName());
                        System.out.println("complete before------" + " thread:"
                                + Thread.currentThread().getName());
                        observer.onCompleted(); // ��������ִ��observer���κη���
                        System.out.println("complete after------" + " thread:"
                                + Thread.currentThread().getName());
                        observer.onCompleted(); // ����ִ�е�
                        observer.onNext("abc"); // ����ִ�е�
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }



    public void testQueue() throws Exception {
        // queue()���첽�Ƕ�����ִ�У�ֱ�ӷ��أ�ͬʱ����һ���߳�����HelloWorldHystrixCommand.run()
        // һ������ֻ��queue()һ��
        // queue()��ʵ����toObservable().toBlocking().toFuture()
        Future<String> future = new HelloWorldHystrixCommand("Hlx").queue();

        // ʹ��futureʱ�����������ȴ�HelloWorldHystrixCommand.run()ִ���귵��
        String queueResult = future.get(10000, TimeUnit.MILLISECONDS);
        // String queueResult = future.get();
        System.out.println("queue�첽�����" + queueResult);
    }

    public void testObservable() throws Exception {

        // observe()���첽�Ƕ�����ִ�У�ͬqueue
        Observable<String> hotObservable = new HelloWorldHystrixCommand("Hlx").observe();

        // single()�Ƕ�����
        //System.out.println("hotObservable single�����" + hotObservable.toBlocking().single());
        //System.out.println("------------------single");
        // ע��۲����¼�
        // subscribe()�ǷǶ�����
        hotObservable.subscribe(new Observer<String>() {

            // ��ִ��onNext��ִ��onCompleted
            // @Override
            public void onCompleted() {
                System.out.println("hotObservable completed");
            }
            // @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
            // @Override
            public void onNext(String v) {
                System.out.println("hotObservable onNext: " + v);
            }
        });
        // �Ƕ���
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        hotObservable.subscribe(new Action1<String>() {

            // �൱�������onNext()
            // @Override
            public void call(String v) {
                System.out.println("hotObservable call: " + v);
            }
        });
        // ���̲߳�ֱ���˳����ڴ�һֱ�ȴ������߳�ִ��
        System.in.read();
    }


    public void testToObservable() throws Exception {

        // toObservable()���첽�Ƕ�����ִ�У�ͬqueue
        Observable<String> coldObservable = new HelloWorldHystrixCommand("Hlx").toObservable();

        // single()�Ƕ�����
        //System.out.println("coldObservable single�����" + coldObservable.toBlocking().single());

        // ע��۲����¼�
        // subscribe()�ǷǶ�����
        // - this is a verbose anonymous inner-class approach and doesn't do assertions
        coldObservable.subscribe(new Observer<String>() {

            // ��ִ��onNext��ִ��onCompleted
            // @Override
            public void onCompleted() {
                System.out.println("coldObservable completed");
            }
            // @Override
            public void onError(Throwable e) {
                System.out.println("coldObservable error");
                e.printStackTrace();
            }
            // @Override
            public void onNext(String v) {
                System.out.println("coldObservable onNext: " + v);
            }
        });
        // �Ƕ���
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        /*coldObservable.subscribe(new Action1<String>() {

            public void call(String v) {
                // �൱�������onNext()
                // @Override
                System.out.println("coldObservable call: " + v);
                }
        });*/
        // ���̲߳�ֱ���˳����ڴ�һֱ�ȴ������߳�ִ��
        System.in.read();

    }

    public static void main(String[] args) {
        Observable<String> observable = new HelloWorldHystrixObservableCommand("test").observe();
        observable.subscribe(new Subscriber<String>() {
            public void onCompleted() {
                System.out.println("completed");
            }

            public void onError(Throwable throwable) {
                System.out.println("error-----------" + throwable);
            }

            public void onNext(String v) {
                System.out.println("next------------" + v);
            }
        });
    }
}
