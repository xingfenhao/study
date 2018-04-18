/*
package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

*/
/**
 * Created by xingfenhao on 2017/3/20.
 *//*

public class TestHelloReflectProx {
    public static void main(String[] args) {
        //代理工具
     MyInvocationHandler demo = new MyInvocationHandler();
        //代理类
        Subject sub = (Subject) demo.bind(new RealSubject());
        String info = sub.say("Rollen", 20);
        System.out.println(info);


        MyInvocationHandler1 demo1 = new MyInvocationHandler1();
        Subject sub1 = (Subject)demo1.bind(new RealSubject());
        System.out.println(sub1.say("Rwewer",29));
        System.exit(0);
    }
}


// 定义真实项目
class RealSubject implements Subject {
    @Override
    public String say(String name, int age) {
        return name + "  " + age;
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj = null;

    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
                .getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object temp = method.invoke(this.obj, args);
        return temp;
    }
}

class MyInvocationHandler1 implements InvocationHandler {

    private Object obj = null;

    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.obj, args);
    }
}*/
