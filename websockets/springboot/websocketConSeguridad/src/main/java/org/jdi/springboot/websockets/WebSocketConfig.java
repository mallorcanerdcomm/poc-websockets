package org.jdi.springboot.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer
{
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registro)
    {
        registro.enableSimpleBroker("/push");
        registro.setApplicationDestinationPrefixes("/pull");
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registro)
    {
        registro.addEndpoint("/datos").withSockJS();
    }
    
}