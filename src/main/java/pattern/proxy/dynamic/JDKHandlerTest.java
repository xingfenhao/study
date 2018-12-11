package pattern.proxy.dynamic;

public class JDKHandlerTest {


    public static void main(String[] args) {
        You you = new You();
        JDKHandler jdkHandler = new JDKHandler(you);
        Marry marry = (Marry)jdkHandler.getProxy();
        marry.marry();
    }

}
