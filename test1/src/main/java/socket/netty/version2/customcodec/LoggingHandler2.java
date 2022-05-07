package socket.netty.version2.customcodec;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class LoggingHandler2 extends LoggingHandler {
    public LoggingHandler2(LogLevel level) {
        super(level);
    }
}
