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
        //最少配置:指定命令组名(CommandGroup)
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        // 依赖逻辑封装在run()方法中
        return "Hello " + name + " thread:" + Thread.currentThread().getName();
    }

    //调用实例
    public static void main(String[] args) throws Exception {

        //使用命令模式封装依赖逻辑
        test_command();

        //注册异步事件回调执行
        test_observable();

        //重载HystrixCommand 的getFallback方法实现逻辑
        test_fallback();

    }

    /**
     * 使用命令模式封装依赖逻辑
     */
    private static void test_command() throws Exception {
        //每个Command对象只能调用一次,不可以重复调用,
        //重复调用对应异常信息:This instance can only be executed once. Please instantiate a new instance.
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Synchronous-hystrix");
        //使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();
        String result = helloWorldCommand.execute();
        System.out.println("test_command-result=" + result);

        helloWorldCommand = new HelloWorldCommand("Asynchronous-hystrix");
        //异步调用,可自由控制获取结果时机,
        Future<String> future = helloWorldCommand.queue();
        //get操作不能超过command定义的超时时间,默认:1秒
        result = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("test_command-result=" + result);
        System.out.println("test_command-mainThread=" + Thread.currentThread().getName());
    }

    /**
     * 注册异步事件回调执行
     */
    private static void test_observable() {
        //注册观察者事件拦截
        Observable<String> fs = new HelloWorldCommand("World").observe();
//注册结果回调事件
        fs.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                //执行结果处理,result 为HelloWorldCommand返回的结果
                //用户对结果做二次处理.
            }
        });
//注册完整执行生命周期事件
        fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // onNext/onError完成之后最后回调
                System.out.println("test_observable-execute onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                // 当产生异常时回调
                System.out.println("test_observable-onError " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                // 获取结果后回调
                System.out.println("test_observable-onNext: " + v);
            }
        });
    }

    /**
     * 使用Fallback() 提供降级策略
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
                    /* 配置依赖超时时间,500毫秒*/
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)));
            this.name = name;
        }

        @Override
        protected String getFallback() {
            return "exeucute Falled";
        }

        @Override
        protected String run() throws Exception {
            //sleep 1 秒,调用会超时
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
                        // 使用不同的线程池做隔离，防止上层线程池跑满，影响降级逻辑.
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
