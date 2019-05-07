package pattern.observe;

import java.util.Observable;

public class SubjectFor3d extends Observable {

    private String msg;

    public String getMsg()
    {
        return msg;
    }

    /**
     * 更新主题
     */
    public  void setMsg(String msg)
    {
        this.msg = msg;
        setChanged();
        notifyObservers();
    }
}
