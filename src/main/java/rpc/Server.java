/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package rpc;

import java.io.IOException;

/**
 * @author fenghao.xing
 * @version : Server.java, v 0.1 2018-08-30 17:48 fenhao.xing Exp $
 */
public interface Server {

    public void stop();

    public void start() throws IOException;

    public void register(Class serviceInterface, Class impl);

    public boolean isRunning();

    public int getPort();

}
