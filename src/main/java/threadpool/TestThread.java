package threadpool;

import java.util.List;
import java.util.Map;

/**
 * Created by xingfenhao on 2017/2/20.
 */
public class TestThread implements Runnable {

    private String command;
    private List<String> list;

    public   TestThread(String command, List<String> list)
    {
        this.command = command;
        this.list = list;
    }

    public void run() {

        System.out.println(Thread.currentThread().getName()+"start  ces 2222222222222 start"+list.toString());
        processCommand();
        System.out.println(Thread.currentThread().getName()+"end  ces 2222222222222 start");

    }

    private void processCommand()
    {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //切割list,多线程处理



    @Override
    public String toString(){
        return this.command;
    }

}
