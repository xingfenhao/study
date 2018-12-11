/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author fenghao.xing
 * @version : KryoDemo.java, v 0.1 2018-08-21 18:30 fenhao.xing Exp $
 */
public class KryoUtil {


    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL =
            new ThreadLocal<Kryo>() {
                @Override
                protected Kryo initialValue() {
                    return new Kryo();
                }
            };

    public static <T> byte[] writeObject(T obj, int maxBufferSize) {
        Kryo kryo = KRYO_THREAD_LOCAL.get();
        Output output = new Output(maxBufferSize);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
        return output.toBytes();
    }

    public static <T> T readObject(byte[] bytes, Class<T> cls) {
        Kryo kryo = KRYO_THREAD_LOCAL.get();
        Input input = new Input(bytes);
        return kryo.readObject(input, cls);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 5; ++i) {
            KryoThread kryoThread = new KryoThread("Thread-" + i);
            kryoThread.start();
        }
    }


    public static class KryoThread extends Thread {
        public KryoThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; ++i) {
                Person person = new Person(i, "name" + i, 20 + i);
                byte[] bytes = KryoUtil.writeObject(person, 100);
                System.out.println(getName() + " writing...");
                Person result = KryoUtil.readObject(bytes, Person.class);
                System.out.println(getName() + " reading...");
                System.out.println("result:  ");
                System.out.println(result.toString());
            }
        }
    }

    public static class Person {
        int age;
        String name;
        int no;

        private Person(int age, String name, int no) {
            this.age = age;
            this.name = name;
            this.no = no;
        }
    }
}
