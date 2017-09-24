package org.jdi.springboot.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer
{
    
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registro)
    {
        System.out.println("---> Registramos el servicio de saludo");
        
        registro.addHandler(new WebSocketSaludoHandler(), "/saludo");
        
        System.out.println("---> Registramos el servicio de contador");
        
        registro.addHandler(new WebSocketContadorHandler(), "/contador");
    }
    
}