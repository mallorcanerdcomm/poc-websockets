package org.jdi.websocket.echo;

import javax.websocket.server.ServerEndpointConfig.Configurator;

public class EchoEndpointConfigurator extends Configurator
{

	private static EchoServerEndpoint echoServer = new EchoServerEndpoint();

	@Override
	public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException
	{
		return (T) this.echoServer;
	}

}