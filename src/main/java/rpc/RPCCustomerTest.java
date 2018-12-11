/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author fenghao.xing
 * @version : RPCTest.java, v 0.1 2018-08-30 17:51 fenhao.xing Exp $
 */
public class RPCCustomerTest {


    public static void main(String[] args) throws IOException {

        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }

}
