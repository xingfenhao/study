import java.util.concurrent.Executors;

/**
 * Created by xingfenhao on 2017/2/15.
 */
public class TestPool {

    public static void main(String[] args) {
        Executors.newFixedThreadPool(9);
        Executors.newSingleThreadExecutor(null);
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(22);
    }
}
