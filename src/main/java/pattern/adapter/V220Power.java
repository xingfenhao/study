package pattern.adapter;

public class V220Power {

    /**
     * 提供220v电压
     * @return
     */
    public int provideV220()
    {
        System.out.println("我提供220V交流电压。");
        return 220;
    }
}
