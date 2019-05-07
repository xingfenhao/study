package pattern.decorator;

import org.slf4j.Logger;

public class MainLog {

    private static Logger logger = JsonLoggerFactory.getLogger(MainLog.class);

    public static void main(String[] args)
    {
        logger.error("hahahaha ");
    }
}
