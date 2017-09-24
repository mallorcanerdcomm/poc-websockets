package org.jdi.springboot.websocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketSaludoHandler extends TextWebSocketHandler
{
    
    private List<WebSocketSession> sessiones = new CopyOnWriteArrayList<>();
    
    @Override
    public void handleTextMessage(WebSocketSession sesion, TextMessage mensaje) throws InterruptedException, IOException
    {
        System.out.println("---> Cliente consumiendo el servicio de saludo");
        
        sesion.sendMessage(new TextMessage("Hola " + mensaje.getPayload() + "!!!"));
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession sesion) throws Exception
    {
        
        System.out.println("---> Cliente conectado al websocket de saludo");
        
        this.sessiones.add(sesion);
    }
    
}