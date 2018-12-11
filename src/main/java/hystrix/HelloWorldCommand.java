/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package hystrix;

import com.netflix.hystrix.*;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author fenghao.xing
 * @version : HelloWorldCommand.java, v 0.1 2018-07-10 10:37 fenhao.xing Exp $
 */
public class HelloWorldCommand extends HystrixCommand<String> {
    private final String name;

    public HelloWorldCommand(String name) {
        //��������:ָ����������(CommandGroup)
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        // �����߼���װ��run()������
        return "Hello " + name + " thread:" + Thread.currentThread().getName();
    }

    //����ʵ��
    public static void main(String[] args) throws Exception {

        //ʹ������ģʽ��װ�����߼�
        test_command();

        //ע���첽�¼��ص�ִ��
        test_observable();

        //����HystrixCommand ��getFallback����ʵ���߼�
        test_fallback();

    }

    /**
     * ʹ������ģʽ��װ�����߼�
     */
    private static void test_command() throws Exception {
        //ÿ��Command����ֻ�ܵ���һ��,�������ظ�����,
        //�ظ����ö�Ӧ�쳣��Ϣ:This instance can only be executed once. Please instantiate a new instance.
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Synchronous-hystrix");
        //ʹ��execute()ͬ�����ô���,Ч����ͬ��:helloWorldCommand.queue().get();
        String result = helloWorldCommand.execute();
        System.out.println("test_command-result=" + result);

        helloWorldCommand = new HelloWorldCommand("Asynchronous-hystrix");
        //�첽����,�����ɿ��ƻ�ȡ���ʱ��,
        Future<String> future = helloWorldCommand.queue();
        //get�������ܳ���command����ĳ�ʱʱ��,Ĭ��:1��
        result = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("test_command-result=" + result);
        System.out.println("test_command-mainThread=" + Thread.currentThread().getName());
    }

    /**
     * ע���첽�¼��ص�ִ��
     */
    private static void test_observable() {
        //ע��۲����¼�����
        Observable<String> fs = new HelloWorldCommand("World").observe();
//ע�����ص��¼�
        fs.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                //ִ�н������,result ΪHelloWorldCommand���صĽ��
                //�û��Խ�������δ���.
            }
        });
//ע������ִ�����������¼�
        fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // onNext/onError���֮�����ص�
                System.out.println("test_observable-execute onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                // �������쳣ʱ�ص�
                System.out.println("test_observable-onError " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                // ��ȡ�����ص�
                System.out.println("test_observable-onNext: " + v);
            }
        });
    }

    /**
     * ʹ��Fallback() �ṩ��������
     */
    private static void test_fallback() {
        InnerHelloWorldCommand command = new InnerHelloWorldCommand("test-Fallback");
        String result = command.execute();
        System.out.println("test_fallback-result=" + result);
    }

    static class InnerHelloWorldCommand extends HystrixCommand<String> {
        private final String name;

        public InnerHelloWorldCommand(String name) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                    /* ����������ʱʱ��,500����*/
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)));
            this.name = name;
        }

        @Override
        protected String getFallback() {
            return "exeucute Falled";
        }

        @Override
        protected String run() throws Exception {
            //sleep 1 ��,���ûᳬʱ
            //TimeUnit.MILLISECONDS.sleep(1000);
            return "Hello " + name + " thread:" + Thread.currentThread().getName();
        }

    }


    static class CommandWithFallbackViaNetwork extends HystrixCommand<String> {
        private final int id;

        protected CommandWithFallbackViaNetwork(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueCommand")));
            this.id = id;
        }

        @Override
        protected String run() {
            // RemoteService.getValue(id);
            throw new RuntimeException("force failure for example");
        }

        @Override
        protected String getFallback() {
            return new FallbackViaNetwork(id).execute();
        }

        private static class FallbackViaNetwork extends HystrixCommand<String> {
            private final int id;

            public FallbackViaNetwork(int id) {
                super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueFallbackCommand"))
                        // ʹ�ò�ͬ���̳߳������룬��ֹ�ϲ��̳߳�������Ӱ�콵���߼�.
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("RemoteServiceXFallback")));
                this.id = id;
            }

            @Override
            protected String run() throws InterruptedException {
                TimeUnit.MILLISECONDS.sleep(1000);
                return  " thread:" + Thread.currentThread().getName();
             }

            @Override
            protected String getFallback() {
                return null;
            }
        }
    }


}
