package org.jdi.websocket.echo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/echo", configurator=EchoEndpointConfigurator.class)
public class EchoServerEndpoint
{

	private Set<Session> sesiones = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void openEvent(Session sesion)
	{
		this.sesiones.add(sesion);
	}

	@OnClose
	public void closeEvent(Session sesion)
	{
		this.sesiones.remove(sesion);
	}

	@OnMessage
	public void messageEvent(String mensaje, Session sesion)
	{
		System.out.println("---> Mensaje recibido: " + mensaje);
		//for (Session auxSesion: sesion){
			System.out.println("---> Enviando a " + sesion.getId());
			sesion.getAsyncRemote().sendText("Echo: " + mensaje);
		//}
	}

}