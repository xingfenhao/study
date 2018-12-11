package pattern.adapter;

/**
 * 适配器，把220v变成5v
 */
public class V5PowerAdapter implements V5Power {

    /**
     * 组合方式
     */
    V220Power v220Power;

    public V5PowerAdapter(V220Power v220Power) {
        this.v220Power = v220Power;
    }

    @Override
    public int provideV5() {

        int provideV220 = v220Power.provideV220();

        //变成V5操作
        System.out.println("适配器：我悄悄的适配了电压。");
        int provideV5 = 5;
        return provideV5;
    }
}
