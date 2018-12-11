package pattern.adapter;

/**
 * 手机充，充电器需要5V电压
 */
public class Mobile {


    public void inputPower(V5Power v5Power)
    {
        int provideV5 = v5Power.provideV5();
        System.out.println("手机（客户端）：我需要5V电压充电，现在是-->" + provideV5 + "V");
    }
}
