/**
 * Created by xingfenhao on 2017/2/13.
 */
public class TestAssert {

    public static void main(String[] args) {
        test1(-4);
        test2(-3);
    }


    private static void test1(int a) {
        assert a > 0;
        System.out.println("test1 assert a=" + a);
    }

    private static void test2(int a) {
        assert a > 0 : "测试断言a小于0";
        System.out.println("test2 assert a=" + a);
    }
}
