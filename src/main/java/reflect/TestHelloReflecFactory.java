/*
package reflect;

import java.io.*;
import java.util.Properties;

*/
/**
 * Created by xingfenhao on 2017/3/20.
 *//*

public class TestHelloReflecFactory {
    public static void main(String[] a) throws   IOException{
        Properties pro=init.getPro();
        fruit f=Factory.getInstance(pro.getProperty("orange"));
        if(f!=null){
            f.eat();
        }
    }
}
interface fruit{
    public abstract void eat();
}

class Apple implements fruit{
    public void eat(){
        System.out.println("Apple");
    }
}

class Orange implements fruit{
    public void eat(){
        System.out.println("Orange");
    }
}

//操作属性文件类
class init{
    public static Properties getPro() throws FileNotFoundException, IOException {
        Properties pro=new Properties();
        File f=new File("fruit1.properties");
        if(f.exists()){
            pro.load(new FileInputStream(f));
        }else{
            pro.setProperty("apple", "reflect.Apple");
            pro.setProperty("orange", "reflect.Orange");
            pro.store(new FileOutputStream(f), "FRUIT CLASS");
        }
        return pro;
    }
}

class Factory{
    public static fruit getInstance(String ClassName){
        fruit f=null;
        try{
            f=(fruit)Class.forName(ClassName).newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}*/
