/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package hystrix.helloexample;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author fenghao.xing
 * @version : SayHelloCommand.java, v 0.1 2018-07-10 14:13 fenhao.xing Exp $
 */
public class SayHelloCommand extends HystrixCommand<String> {

    private final String _name;

    public SayHelloCommand(String name)
    {
        //builder for HystrixCommand
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloServiceGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)));
        _name = new String(name);

    }
    @Override
    protected String getFallback() {
        return String.format("[FallBack]Hello %s!", _name);
    }

    @Override
    protected String run() throws Exception {
        //TimeOut
        Thread.sleep(400);
        return String.format("Hello %s!", _name);
    }
}
