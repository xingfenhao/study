package pattern.proxy.staticsproxy;

public class UserDao implements IUserDao {


    @Override
    public void save() {
        System.out.println("save data succ");
    }
}
