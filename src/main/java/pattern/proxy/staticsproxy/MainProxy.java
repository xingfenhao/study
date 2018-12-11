package pattern.proxy.staticsproxy;

public class MainProxy {
    public static void main(String[] args)
    {

        IUserDao userDao = new UserDao();
        UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
        userDaoProxy.save();
    }
}
