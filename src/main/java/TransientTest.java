import java.io.*;

/**
 * Created by xingfenhao on 2017/2/10.
 */
public class TransientTest {
    public static void main(String[] args) {

        User user = new User();
        user.setUserName("Alexia");
        user.setPassWord("123456");
        user.setNO("adadfafdNO===NO");

        System.out.println("read before Serializable: ");
        System.out.println("username: " + user.getUserName());
        System.out.println("password: " + user.getPassWord());
        System.out.println("password: " + user.getNO());

        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream("G:\\xingfenhao\\study\\test\\user.txt"));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            user.setNO("NO改变后的信息，没有被反序列化");
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "G:\\xingfenhao\\study\\test\\user.txt"));
            User user1 = (User) is.readObject(); // 从流中读取User的数据
            is.close();

            System.out.println("\nread after Serializable: ");
            System.out.println("username: " + user1.getUserName());
            System.out.println("password: " + user1.getPassWord());
            System.out.println("NO: " + user1.getNO());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


class User implements Serializable {
    private static final long serialVersionUID = 8294180014912103005L;
    public String getUserName() {
        return userName;
    }
    User()
    {
        userName="1";
        passWord ="323ww";
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    private String userName;
    private String age;

    public   String getNO() {
        return NO;
    }

    public   void setNO(String NO) {
        this.NO = NO;
    }

    private String NO;
    private transient String passWord;
}

