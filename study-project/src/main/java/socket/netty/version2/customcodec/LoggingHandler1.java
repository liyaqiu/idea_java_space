package socket.netty.version2.customcodec;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class LoggingHandler1 extends LoggingHandler {
    public LoggingHandler1(LogLevel level) {
        super(level);
    }
}
