import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by xingfenhao on 2017/2/14.
 */
public class TestSet {

    Set a = new LinkedHashSet();

    public static void main(String[] args){
        Set a1 = new HashSet();
        System.out.println(a1.add("adadf"));

        float v = 1.0f;
        int v1 = 1;
        System.out.println(v != v);
        System.out.println(v1 != v1);
    }
}
