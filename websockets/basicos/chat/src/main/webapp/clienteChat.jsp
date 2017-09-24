<html>
	<head>
		<title>Chat Websocket</title>
		<meta name="viewport" conent="width=device-width" />
	</head>
	<body>
		<div>
			<input type="text" id="entrada" />
		</div>
		<div>
			<button type="button" onClick="abrirSocket();">Abrir</button>
			<button type="button" onCLick="enviar();">Enviar</button>
			<button type="button" onClick="cerrarSocket();">Cerrar</button>
		</div>
		<div id="mensajes"></div>

		<script>
			var websocket;
			var mensajes = document.getElementById("mensajes");

			function abrirSocket(){
				if (websocket != undefined){
					return;
				}
				
				websocket = new WebSocket("ws://localhost:8080/chatWebsocket/chat");

				websocket.onopen = function(event)
				{
					if (event.data == undefined){
						return;
					}

					escribirRespuesta(event.data);
				}

				websocket.onmessage = function(event)
				{
					escribirRespuesta(event.data);
				}

				websocket.onclose = function(event)
				{
					escribirRespuesta("Usuario desconectado");
				}
			}

			function enviar()
			{
				var texto = document.getElementById("entrada").value;
				websocket.send(texto);
			}

			function cerrarSocket()
			{
				websocket.close();
				websocket = undefined;
			}

			function escribirRespuesta(texto)
			{
				mensajes.innerHTML += "<br/>" + texto;
			}
		</script>
	</body>
</html>
