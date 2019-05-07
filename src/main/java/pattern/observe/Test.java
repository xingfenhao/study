package pattern.observe;

public class Test {

    public static void main(String[] args)
    {
        SubjectFor3d subjectFor3d = new SubjectFor3d() ;

        Observer1 observer1 = new Observer1();
        observer1.registerSubject(subjectFor3d);
        Observer2 observer2 = new Observer2();
        observer2.registerSubject(subjectFor3d);

        subjectFor3d.setMsg("hello 3d'nums : 110 ");

    }
}
