package threadpool;

/**
 * Created by xingfenhao on 2017/3/13.
 */
class Resource {  //生产者和消费者都要操作的资源
    private String name;
    private int count = 1;
    private boolean flag = false;

    public synchronized void set(String name) {
        if (flag)
            try {
                wait();
            } catch (Exception e) {
            }
        this.name = name + "---" + count++;
        System.out.println(Thread.currentThread().getName() + "...生产者..." + this.name);
        flag = true;
        this.notify();
    }

    public synchronized void out() {
        if (!flag)
            try {
                wait();
            } catch (Exception e) {
            }
        System.out.println(Thread.currentThread().getName() + "...消费者..." + this.name);
        flag = false;
        this.notify();
    }
}

class Producer implements Runnable {
    private Resource res;

    Producer(Resource res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            res.set("商品");
        }
    }
}

class Consumer implements Runnable {
    private Resource res;

    Consumer(Resource res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            res.out();
        }
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Resource r = new Resource();
        Producer pro1 = new Producer(r);
        Producer pro2 = new Producer(r);

        Consumer con1 = new Consumer(r);
        Consumer con2 = new Consumer(r);
        Thread t11 = new Thread(pro1);
        Thread t12 = new Thread(pro2);
        Thread t21 = new Thread(con1);
        Thread t22 = new Thread(con2);
        int i = 0;
        while (i < 50) {
            t11.start();
            t12.start();
            t21.start();
            t22.start();
            i++;
        }

        t11.interrupt();

        t12.interrupt();

        t21.interrupt();

        t22.interrupt();

    }
}//运行结果正常，生产者生产一个商品，紧接着消费者消费一个商品。
/*
但是如果有多个生产者和多个消费者，上面的代码是有问题，比如2个生产者，2个消费者，
        运行结果就可能出现生产的1个商品生产了一次而被消费了2次，或者连续生产2个商品而只有1个被消费，
        这是因为此时共有4个线程在操作Resource对象r,  而notify()唤醒的是线程池中第1个wait()的线程，
        所以生产者执行notify()时，唤醒的线程有可能是另1个生产者线程，
        这个生产者线程从wait()中醒来后不会再判断flag，
        而是直接向下运行打印出一个新的商品，这样就出现了连续生产2个商品。*/
