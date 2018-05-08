/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package threadpool;

import java.util.concurrent.*;

/**
 * @author fenghao.xing
 * @version : CallableAndFuture.java, v 0.1 2018-05-08 15:43 fenhao.xing Exp $
 */
public class CallableAndFuture {
    public static void main(String[] args)
    {

       Callable callAble = new Callable() {
           @Override
           public Object call() throws Exception {
               return "testCallAble111";
           }
       };
        FutureTask futureTask = new FutureTask(callAble);


        //普通线程
        /*
        new Thread(futureTask).run();

        try {
            Thread.sleep(500);
            System.out.println(futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

      //用线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(futureTask);
        try {
            System.out.println(futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
