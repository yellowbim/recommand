package jjuni.pjt.recommend.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer {
    // websocket connect 를 위한설정 (handshake를 여기서 진행)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat")
//                .setAllowedOrigins("*")
                .setAllowedOriginPatterns("*", "http://localhost:3000")
                .withSockJS();
    }

    // 메시지를 받을 경로 설정 (/test 들어온 STOPM 메시지는 @Controller객체의 @MessageMapping 메서드로 라우팅됨)
    // 또한 /topic, /queue로 Broadcasting 된다
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/pub");
        config.enableSimpleBroker("/sub");
    }
}