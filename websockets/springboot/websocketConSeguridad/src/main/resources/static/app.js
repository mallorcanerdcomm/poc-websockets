var cliente = null;

function conectarse(conectado)
{
	$("#conectar").prop("disabled", conectado);
	$("#desconectar").prop("disabled", !conectado);
	if (conectado){
		$("#datos").show();
	} else {
		$("#datos").hide();
	}
	$("#datosMemoria").html("");
}

function conectar()
{
	conectarse(true);
	var socket = new SockJS("/datos");
	cliente = Stomp.over(socket);
	cliente.connect({}, 
	function (frame){
		var sessionId = /\/([^\/]+)\/websocket/.exec(socket._transport.url)[1];

		cliente.subscribe("/push/errores", function (mensaje){
			alert("Error: " + mensaje.body);
		});

		cliente.subscribe("/push/reply-" + sessionId, function (mensaje){
			mostrar(JSON.parse(mensaje.body).total, JSON.parse(mensaje.body).maxima, JSON.parse(mensaje.body).libre);
		});
	}, 
	function (error){
		alert("Error STOMP: " + error);
	});
}

function desconectar()
{
	if (cliente != null){
		cliente.disconnect();
	}
	conectarse(false);
}

function preguntar()
{
	cliente.send("/pull/memoria", {}, "datos");
}

function mostrar(total, maxima, libre)
{
	$("#datosMemoria").append("<tr>");
	$("#datosMemoria").append("<td>" + total + "</td>");
	$("#datosMemoria").append("<td>" + maxima + "</td>");
	$("#datosMemoria").append("<td>" + libre + "</td>");
	$("#datosMemoria").append("</tr>");
}

$(function()
{
	$("form").on("submit", function (e){
		e.preventDefault();
	});
	$("#conectar").click(function(){
		conectar();
	});
	$("#desconectar").click(function(){
		desconectar();
	});
	$("#preguntar").click(function(){
		preguntar();
	});
});

