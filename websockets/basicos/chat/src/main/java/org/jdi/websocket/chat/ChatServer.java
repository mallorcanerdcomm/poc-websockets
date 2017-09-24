package org.jdi.websocket.chat;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatServer
{
    
    private static final Set<Session> sesiones = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void abrirSocket(Session sesion)
    {
        System.out.println("---> " + sesion.getId() + " se ha conectado");
        this.enviarMasivamente("Usuario conectado");
        this.sesiones.add(sesion);
    }
    
    @OnClose
    public void cerrarSocket(Session sesion)
    {
        this.sesiones.remove(sesion);
        System.out.println("---> " + sesion.getId() + " ha finalizado su conexion");
        this.enviarMasivamente("Usuario desconectado");
    }
    
    @OnMessage
    public void enviarMensaje(String mensaje, Session sesion)
    {
        System.out.println("---> " + sesion.getId() + " ha envado el mensaje - " + mensaje);
        this.enviarMasivamente(mensaje);
    }
    
    private void enviarMasivamente(String mensaje)
    {
        try{
            for (Session sesion: this.sesiones){
                sesion.getBasicRemote().sendText(mensaje);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
