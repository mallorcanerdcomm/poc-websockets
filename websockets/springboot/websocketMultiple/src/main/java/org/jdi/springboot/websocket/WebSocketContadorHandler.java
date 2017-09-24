package org.jdi.springboot.websocket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketContadorHandler extends TextWebSocketHandler
{
    
    private List<WebSocketSession> sessiones = new CopyOnWriteArrayList<>();
    
    @Override
    public void afterConnectionEstablished(WebSocketSession sesion) throws Exception
    {
        
        System.out.println("---> Cliente conectado al websocket de contador");
        
        this.sessiones.add(sesion);
        
        for (int i=0; i<Integer.MAX_VALUE; i++){
            sesion.sendMessage(new TextMessage("Contador: " + i));
            Thread.sleep(10 * 1000);
        }
    }
    
}