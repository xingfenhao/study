import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xingfenhao on 2017/2/8.
 */
public class TestArrylist {




public static void  main(String[] args)
    {

        List l = Arrays.asList(args);

        System.out.println(l.toArray());
        System.out.println(l.toArray(new Object[0]));


        List testArrL = new  ArrayList();
        testArrL.add("2");
        testArrL.add("6");


        List testArrL1 = new  ArrayList();
        testArrL1.add("2");
        testArrL1.add("3");


        System.out.println(testArrL.retainAll(testArrL1));
        System.out.println(testArrL);
        System.out.println(testArrL1);


        List testArrL2 = new  ArrayList();
        testArrL2.add("aaa");
        testArrL2.add("22");
        testArrL2.add("23");
        testArrL2.add("24");
        testArrL2.add("25");
        testArrL2.add("26");


        List testArrL3 = new  ArrayList();
        testArrL3.add("aaa");
        testArrL3.add("12");
        testArrL3.add("13");
        testArrL3.add("14");
        testArrL3.add("16");
        testArrL3.add("17");


        testArrL2.retainAll(testArrL3);

        System.out.println(testArrL2.retainAll(testArrL3));
        System.out.println(testArrL2);
        System.out.println(testArrL3);
    }



}
