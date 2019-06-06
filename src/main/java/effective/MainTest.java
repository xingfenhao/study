package effective;

public class MainTest   {

    public static void main(String[] args) {

        double x = 1.1;
        double y = 1.2;

        //double result = EnumReplaceInt.PLUS.apply(x, y);

        double result = EnumReplaceInt1.PLUS.apply(x, y);

        System.out.println(result);
    }

}
