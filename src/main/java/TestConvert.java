/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 对象转换
 * @author fenghao.xing
 * @version : TestConvert.java, v 0.1 2018-04-02 17:07 fenhao.xing Exp $
 */
public class TestConvert {

    public static void main(String[] args){
        TestObject1 testObject1 = new TestObject1();
        testObject1.setAge(10);
        testObject1.setName("test1");
        testObject1.setSex("男");
       /* Class clz = testObject1.getClass();
        Method[] mArry = clz.getDeclaredMethods();
        for(Method method : mArry)
        {
            System.out.println(method.getName()+"----"+method.getDefaultValue());

        }*/
        StringBuilder sbName = new StringBuilder();
        StringBuilder sbValue = new StringBuilder();
        String[] fieldNames = getFiledName(testObject1);

        for(int j=0 ; j<fieldNames.length ; j++){     //遍历所有属性
            String name = fieldNames[j];    //获取属性的名字
            Object value = getFieldValueByName(name,testObject1);
            sbName.append(name);
            sbValue.append(value);
            if(j != fieldNames.length - 1) {
                sbName.append("/");
                sbValue.append("/");
            }


        }

        System.out.println("attribute name:"+sbName.toString());
        System.out.println("attribute value:"+sbValue.toString());


    }

    /**
     * 获取属性名数组
     * */
    private static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            System.out.println(fields[i].getType());
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /* 根据属性名获取属性值
     * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {

            return null;
        }
    }

}





class TestObject1{
    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>sex</tt>.
     *
     * @return property value of sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Setter method for property <tt>sex</tt>.
     *
     * @param sex value to be assigned to property sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private String sex;
    private int age;
}

class TestObject2{
    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>sex</tt>.
     *
     * @return property value of sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Setter method for property <tt>sex</tt>.
     *
     * @param sex value to be assigned to property sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private String sex;
    private int age;
}