package pattern.decorator;

import org.slf4j.Logger;

public class JsonLogger extends DecoratorLogger {

    public JsonLogger(Logger logger) {

        super(logger);
}

    @Override
    public void info(String msg) {

        String result = composeBasicJsonResult();
        logger.info(result+msg);
    }

    @Override
    public void error(String msg) {

        String result = composeBasicJsonResult();
        logger.error(result+msg);
    }

    private String composeBasicJsonResult() {
        //拼装了一些运行时信息
        return "test";
    }
}
