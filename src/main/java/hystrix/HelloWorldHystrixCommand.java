/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * https://www.cnblogs.com/cowboys/p/7655829.html
 *
 * @author fenghao.xing
 * @version : HelloWorldHystrixCommand.java, v 0.1 2018-04-16 17:06 fenhao.xing Exp $
 */
public class HelloWorldHystrixCommand extends HystrixCommand<String> {

    private final String name;

    protected HelloWorldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "hello"+name;
    }

    public static void main(String[] agrs) {
        String result = new HelloWorldHystrixCommand("test").execute();
        System.out.println(result);
    }
}
