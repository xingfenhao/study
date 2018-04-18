/*
package threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

*/
/**
 * Created by xingfenhao on 2017/3/14.
 *//*

public class CountDownLatchTest {

    // 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {

        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);

        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);

        // 十名选手
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int index = 0; index < 10; index++) {
            final int NO = index + 1;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 如果当前计数为零，则此方法立即返回。
                        // 等待
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + NO + " arrived");
                    } catch (InterruptedException e) {
                    } finally {
                        // 每个选手到达终点时，end就减一
                        end.countDown();
                    }
                }
            };
            exec.submit(run);
        }
        System.out.println("Game Start");
        // begin减一，开始游戏
        begin.countDown();
        // 等待end变为0，即所有选手到达终点
        end.await();
        System.out.println("Game Over");
        exec.shutdown();
    }
}

*/
/*
使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断或超出了指定的等待时间。如果当前计数为零，则此方法立刻返回 true 值。

        如果当前计数大于零，则出于线程调度目的，将禁用当前线程，且在发生以下三种情况之一前，该线程将一直处于休眠状态：

        由于调用 countDown() 方法，计数到达零；或者
        其他某个线程中断当前线程；或者
        已超出指定的等待时间。

        如果计数到达零，则该方法返回 true 值。

        如果当前线程：

        在进入此方法时已经设置了该线程的中断状态；或者
        在等待时被中断，

        则抛出 InterruptedException，并且清除当前线程的已中断状态。如果超出了指定的等待时间，则返回值为 false。如果该时间小于等于零，则此方法根本不会等待。



        参数：
        timeout - 要等待的最长时间
        unit - timeout 参数的时间单位。
        返回：
        如果计数到达零，则返回 true；如果在计数到达零之前超过了等待时间，则返回 false
        抛出：
        InterruptedException - 如果当前线程在等待时被中断*//*


*/
