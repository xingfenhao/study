/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package hystrix.helloexample;

/**
 * @author fenghao.xing
 * @version : Client.java, v 0.1 2018-07-10 14:08 fenhao.xing Exp $
 */
//�ͻ���ֱ�ӵ���API
public class Client {
    public static void main(String[] args) {

        //ͬ��
        System.out.println(HelloService.sayHello("World"));

        //�첽
       // System.out.println(HelloService.sayHelloAsync("World"));
    }
}
