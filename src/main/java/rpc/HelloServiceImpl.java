/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package rpc;

/**
 * @author fenghao.xing
 * @version : HelloServiceImpl.java, v 0.1 2018-08-30 17:47 fenhao.xing Exp $
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHi(String name) {
        return name;
    }
}
