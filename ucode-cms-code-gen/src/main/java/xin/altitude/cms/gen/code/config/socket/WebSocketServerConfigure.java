package xin.altitude.cms.gen.code.config.socket;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import xin.altitude.cms.common.constant.Constants;

// @Configuration
// @EnableWebSocket
@ConditionalOnClass(WebSocketConfigurer.class)
public class WebSocketServerConfigure implements WebSocketConfigurer {

    private final WebSocketHandler socketHandler = new MyStringWebSocketHandler();

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, Constants.UNIFORM_PREFIX + "/connect").withSockJS();
    }
}
