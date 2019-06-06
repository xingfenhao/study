package effective;

public enum EnumReplaceInt {

    PLUS, MINUS, TIMES, DIVIDE;

    double apply(double x,double y)
    {

        switch (this)
        {
            case PLUS :return x+y;
            case MINUS:return x-y;
            case TIMES:return x*y;
            case DIVIDE:return x/y;
        }

        throw new AssertionError("Unknow op:" + this);

    }
}
