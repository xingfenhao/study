import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Created by xingfenhao on 2017/2/15.
 */
public class TestString {


    public static void main(String[] args) {
        String a1 = "3";
        String a2 = "4";
        String a = "3" + "2" + "1";
        String aa = a1 + a2;
        String aaa = "5" + a1 + a2;
        StringBuilder a333 ;
        System.out.println(a);

        BigDecimal a11 = new BigDecimal(-1);
        System.out.println(a11);
        System.out.println(a11.abs());

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr0 = dateformat.format(System.currentTimeMillis());
        String dateStr1 = dateformat.format(System.currentTimeMillis()+5000);
        System.out.print(dateStr0+"        "+dateStr1);
    }
}
