import com.sun.org.apache.xml.internal.security.c14n.helper.AttrCompare;

import java.util.*;

/**
 * Created by xingfenhao on 2017/2/14.
 */
public class TestMap {


    public static void  main(String[] args)
    {
        Map a = new LinkedHashMap();
        Map a2 = new HashMap();

        a2.put("a","333A");
        a2.put("b","333B");
        a2.put("c","333C");

        Map a1 = new TreeMap();

        a1.put("21","333");
        a1.put("2","333");
        a1.put("22","333");
        System.out.println(a1);

        Comparator   cpr = new AttrCompare();
        cpr.compare(1,2);

    }
}
