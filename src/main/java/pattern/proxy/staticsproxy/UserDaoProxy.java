package pattern.proxy.staticsproxy;

public class UserDaoProxy implements IUserDao {

    IUserDao userDao;

    public UserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {

        System.out.println("start transaction");
        userDao.save();
        System.out.println("end transaction");
    }
}
