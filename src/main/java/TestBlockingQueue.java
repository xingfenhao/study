import java.util.concurrent.*;

/**
 * Created by xingfenhao on 2017/2/15.
 */
public class TestBlockingQueue {
    public static void main()
    {
        BlockingQueue a  = new LinkedBlockingQueue();
        BlockingQueue b = new ArrayBlockingQueue(4);
        BlockingQueue c = new PriorityBlockingQueue();
        BlockingQueue d = new DelayQueue();

    }
}
