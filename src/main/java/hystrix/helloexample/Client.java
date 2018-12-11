/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package hystrix.helloexample;

/**
 * @author fenghao.xing
 * @version : Client.java, v 0.1 2018-07-10 14:08 fenhao.xing Exp $
 */
//客户端直接调用API
public class Client {
    public static void main(String[] args) {

        //同步
        System.out.println(HelloService.sayHello("World"));

        //异步
       // System.out.println(HelloService.sayHelloAsync("World"));
    }
}
