package threadpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xingfenhao on 2017/3/10.
 */
public class SimpleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService =   Executors.newFixedThreadPool(5);

        List<String> lists = new ArrayList<String>();
        Map<Integer,List<String>> map = new HashMap<Integer, List<String>>();
        for(int j = 1;j<100;j++)
        {
            lists.add(j+"test");
        }

        List<String> lists1 = new ArrayList<String>();
        map.put(1,lists1);
        //拆分成10个list
        for(int j = 1;j<=10;j++)
        {
            for(int j1 = 1;j1<100;j1++)
            {
                lists1.add(j1+"test");
                if(j1%j == 0)
                {
                     lists1 = new ArrayList<String>();
                     map.put(j,lists1);
                    continue;
                }
            }
        }

        for(int i=1; i<=10;i++)
        {
            TestThread  testThread = new TestThread(i+"",map.get(i));
            executorService.execute(testThread);
        }
        executorService.shutdown();
    }
}
