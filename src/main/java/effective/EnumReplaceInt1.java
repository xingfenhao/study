package effective;

public enum EnumReplaceInt1 {

    PLUS {
        double apply(double x, double y) {
            return x + y;
        }
    },

    MINUS {
        double apply(double x, double y) {
            return x - y;
        }
    };

    abstract double apply(double x, double y);
    }
