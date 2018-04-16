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
                        observer.onError(getExecutionException()); // 直接抛异常退出，不会往下执行
                        observer.onNext("Hello1" + " thread:" + Thread.currentThread().getName());
                        observer.onNext("Hello2" + " thread:" + Thread.currentThread().getName());
                        observer.onNext(name + " thread:" + Thread.currentThread().getName());
                        System.out.println("complete before------" + " thread:"
                                + Thread.currentThread().getName());
                        observer.onCompleted(); // 不会往下执行observer的任何方法
                        System.out.println("complete after------" + " thread:"
                                + Thread.currentThread().getName());
                        observer.onCompleted(); // 不会执行到
                        observer.onNext("abc"); // 不会执行到
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }



    public void testQueue() throws Exception {
        // queue()是异步非堵塞性执行：直接返回，同时创建一个线程运行HelloWorldHystrixCommand.run()
        // 一个对象只能queue()一次
        // queue()事实上是toObservable().toBlocking().toFuture()
        Future<String> future = new HelloWorldHystrixCommand("Hlx").queue();

        // 使用future时会堵塞，必须等待HelloWorldHystrixCommand.run()执行完返回
        String queueResult = future.get(10000, TimeUnit.MILLISECONDS);
        // String queueResult = future.get();
        System.out.println("queue异步结果：" + queueResult);
    }

    public void testObservable() throws Exception {

        // observe()是异步非堵塞性执行，同queue
        Observable<String> hotObservable = new HelloWorldHystrixCommand("Hlx").observe();

        // single()是堵塞的
        //System.out.println("hotObservable single结果：" + hotObservable.toBlocking().single());
        //System.out.println("------------------single");
        // 注册观察者事件
        // subscribe()是非堵塞的
        hotObservable.subscribe(new Observer<String>() {

            // 先执行onNext再执行onCompleted
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
        // 非堵塞
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        hotObservable.subscribe(new Action1<String>() {

            // 相当于上面的onNext()
            // @Override
            public void call(String v) {
                System.out.println("hotObservable call: " + v);
            }
        });
        // 主线程不直接退出，在此一直等待其他线程执行
        System.in.read();
    }


    public void testToObservable() throws Exception {

        // toObservable()是异步非堵塞性执行，同queue
        Observable<String> coldObservable = new HelloWorldHystrixCommand("Hlx").toObservable();

        // single()是堵塞的
        //System.out.println("coldObservable single结果：" + coldObservable.toBlocking().single());

        // 注册观察者事件
        // subscribe()是非堵塞的
        // - this is a verbose anonymous inner-class approach and doesn't do assertions
        coldObservable.subscribe(new Observer<String>() {

            // 先执行onNext再执行onCompleted
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
        // 非堵塞
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        /*coldObservable.subscribe(new Action1<String>() {

            public void call(String v) {
                // 相当于上面的onNext()
                // @Override
                System.out.println("coldObservable call: " + v);
                }
        });*/
        // 主线程不直接退出，在此一直等待其他线程执行
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
