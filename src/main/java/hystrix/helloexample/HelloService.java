/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package hystrix.helloexample;

import java.util.concurrent.Future;

/**
 * @author fenghao.xing
 * @version : HelloService.java, v 0.1 2018-07-10 14:07 fenhao.xing Exp $
 */
public class HelloService {
/*    public static String sayHello(final String name) {
        return String.format("Hello %s!", name);
    }*/

    public static String sayHello(final String name)
    {
        return new SayHelloCommand(name).execute();
    }

    /**
     * call async
     * @param name
     * @return
     */
    public static Future<String> sayHelloAsync(final String name)
    {
        return new SayHelloCommand(name).queue();
    }
}
