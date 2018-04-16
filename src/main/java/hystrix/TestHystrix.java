/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package hystrix;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fenghao.xing
 * @version : TestHystrix.java, v 0.1 2018-04-16 15:10 fenhao.xing Exp $
 */
public class TestHystrix {
    public static int j = 0;
    public static List list = new ArrayList(100);

    public static void main(String[] agrs)
    {
        ExecutorService executorService =   Executors.newFixedThreadPool(100);
         int i = 1000000000;
        List list = new ArrayList();

         for(int j=0;j<i;j++)
         {
             executorService.execute(new HystrixThread(j));         }
    }

   static class HystrixThread implements Runnable{


        @Override
        public void run() {
            if(j%999 == 1)
            System.out.println(Thread.currentThread().getName()+"="+j);
        }

        public HystrixThread( int j)
        {
            TestHystrix.j = j;
            TestHystrix.list.add(j);
        }

    }

}
