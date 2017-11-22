import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xingfenhao on 2017/2/10.
 */
public class TestRandomAccess {

public static void  main(String[] agrs)
    {
        new TestRandomAccess().testTraverse();
    }
    public   void testTraverse() {
        ArrayList<Integer> arraylist = new ArrayList<Integer>();
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        initList(arraylist, 1000);
        initList(linkedList, 1000);

        System.out.println("ArrayList实现了RandomAccess接口");
        implRandomAccessTraverse(arraylist); //花了10ms时间
        System.out.println("LinkedList未实现了RandomAccess接口");
        implRandomAccessTraverse(linkedList); //花了434ms时间


        System.out.println("\nArrayList实现了RandomAccess接口");
        noImplRandomAccessTraverse(arraylist); //花了39ms时间
        System.out.println("LinkedList未实现了RandomAccess接口");
        noImplRandomAccessTraverse(linkedList); //花了27ms时间
    }

    private long startTime = 0;
    private long endTime = 0;
    // 初始化列表
    public void initList(List<Integer> list, int n) {
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
    }

    //有实现RandomAccess接口的遍历全部数据,
    public void implRandomAccessTraverse(List list) {
        startTime = System.currentTimeMillis();
        for (int count = 0; count <= 1000; count++) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("使用loop迭代一共花了" + (endTime - startTime) + "ms时间");
    }

    //没有实现RandomAccess接口的遍历全部数据
    public void noImplRandomAccessTraverse(List list) {
        startTime = System.currentTimeMillis();
        for (int count = 0; count <= 1000; count++) {
            for (Iterator itr = list.iterator(); itr.hasNext();) {
                itr.next();
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("使用Iterator迭代一共花了" + (endTime - startTime) + "ms时间");
    }

}
