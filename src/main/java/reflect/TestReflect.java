package reflect;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * Created by xingfenhao on 2017/3/20.
 */
public class TestReflect implements Serializable {
    private static final long serialVersionUID = -2862585049955236612L;
    public static void main(String[] args) throws Exception {
       /* TestReflect testReflect = new TestReflect();
        System.out.println(testReflect.getClass().getName());*/
        // ��� reflect.TestReflect


        //ʵ����class����

       /* Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;
        // һ�����������ʽ
        class1 = Class.forName("reflect.TestReflect");
        class2 = new TestReflect().getClass();
        class3 = TestReflect.class;
        System.out.println("������   " + class1.getName());
        System.out.println("������   " + class2.getName());
        System.out.println("������   " + class3.getName());*/


        //��ȡһ������ĸ�����ʵ�ֵĽӿ�
       /* Class<?> clazz = Class.forName("reflect.TestReflect");
        // ȡ�ø���
        Class<?> parentClass = clazz.getSuperclass();
        System.out.println("clazz�ĸ���Ϊ��" + parentClass.getName());
        // clazz�ĸ���Ϊ�� java.lang.Object
        // ��ȡ���еĽӿ�
        Class<?> intes[] = clazz.getInterfaces();
        System.out.println("clazzʵ�ֵĽӿ��У�");
        for (int i = 0; i < intes.length; i++) {
            System.out.println((i + 1) + "��" + intes[i].getName());
        }*/
        // clazzʵ�ֵĽӿ��У�
        // 1��java.io.Serializable

//ͨ���������ʵ����һ����Ķ���
        Class<?> class1 = null;
        class1 = Class.forName("reflect.User");
        // ��һ�ַ�����ʵ����Ĭ�Ϲ��췽��������set��ֵ
        User user = (User) class1.newInstance();
        user.setAge(20);
        user.setName("Rollen");
        System.out.println(user);
        // ��� User [age=20, name=Rollen]
        // �ڶ��ַ��� ȡ��ȫ���Ĺ��캯�� ʹ�ù��캯����ֵ
        Constructor<?> cons[] = class1.getConstructors();
        // �鿴ÿ�����췽����Ҫ�Ĳ���
        for (int i = 0; i < cons.length; i++) {
            Class<?> clazzs[] = cons[i].getParameterTypes();
            System.out.print("cons[" + i + "] (");
            for (int j = 0; j < clazzs.length; j++) {
                if (j == clazzs.length - 1)
                    System.out.print(clazzs[j].getName());
                else
                    System.out.print(clazzs[j].getName() + ",");
            }
            System.out.println(")");
        }
        // ���
        // cons[0] (java.lang.String)
        // cons[1] (int,java.lang.String)
        // cons[2] ()
        user = (User) cons[0].newInstance("Rollen");
        System.out.println(user);
        // ��� User [age=0, name=Rollen]
        user = (User) cons[1].newInstance(20, "Rollen");
        System.out.println(user);
        // ��� User [age=20, name=Rollen]

    }
}

class User {
    private int age;
    private String name;
    public User() {
        super();
    }
    public User(String name) {
        super();
        this.name = name;
    }
    public User(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User [age=" + age + ", name=" + name + "]";
    }
}