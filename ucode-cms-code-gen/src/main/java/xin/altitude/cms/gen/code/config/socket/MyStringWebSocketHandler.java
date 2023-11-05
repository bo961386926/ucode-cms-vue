package xin.altitude.cms.gen.code.config.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import xin.altitude.cms.common.util.WebSocketUtils;

// @Component
public class MyStringWebSocketHandler extends TextWebSocketHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        WebSocketUtils.add(session);
        log.info("和客户端建立连接");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.close(CloseStatus.SERVER_ERROR);
        log.error("连接异常", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        WebSocketUtils.remove(session);
        log.info("和客户端断开连接");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获取到客户端发送过来的消息
        String receiveMessage = message.getPayload();
        log.info(receiveMessage);
    }
}
