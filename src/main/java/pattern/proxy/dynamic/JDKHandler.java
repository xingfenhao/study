package pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理：
 * 1. 目标对象要有接口，且最后创建的代理对象要转换成此接口类型，来调用方
 * 2. 动态代理类实现InvocationHandler接口，持有目标对象引用,利用构造器动态传入目标对象
 * 3. 使用proxy.newProxyInstance()来动态地创建代理对象
 * 4. 代理对象重写invoke方法，利用反射调用目标对象的方法，同时增加行为
 */
public class JDKHandler implements InvocationHandler {

    //目标对象引用
    private Object target;

    //构造器，可以动态传入目标对象
    public JDKHandler(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        Object result = null;
        try {
            before();
            result = method.invoke(target, args);
            after();
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
        return result;

    }

    private void after() {
        System.out.println("get after work done");
    }

    private void before() {
        System.out.println("get ready for marry");
    }
}
