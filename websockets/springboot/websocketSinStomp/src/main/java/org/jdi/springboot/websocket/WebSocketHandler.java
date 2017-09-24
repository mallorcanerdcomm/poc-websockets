package org.jdi.springboot.websocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler
{
    
    private List<WebSocketSession> sessiones = new CopyOnWriteArrayList<>();
    
    @Override
    public void handleTextMessage(WebSocketSession sesion, TextMessage mensaje) throws InterruptedException, IOException
    {
        sesion.sendMessage(new TextMessage("Hola " + mensaje.getPayload() + "!!!"));
        
        Thread.sleep(10 * 1000);
        
        sesion.sendMessage(new TextMessage("Hola " + mensaje.getPayload() + " repeated!!!"));
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession sesion) throws Exception
    {
        
        System.out.println("---> Cliente conectado");
        
        this.sessiones.add(sesion);
    }
    
}