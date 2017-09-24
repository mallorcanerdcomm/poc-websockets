package org.jdi.springboot.websockets;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class MemoriaController
{
    
    @Autowired
    private SimpMessageSendingOperations plantilla;
    
    @MessageMapping("/memoria")
    public Datos obtenerDatosMemoria(@Payload String mensaje, @Header("simpSessionId") String sessionId) throws Exception
    {
        
        System.out.println("---> Nos ha llegado una peticion");
        
        Datos datos = new Datos();
        datos.setTotal(Runtime.getRuntime().totalMemory());
        datos.setMaxima(Runtime.getRuntime().maxMemory());
        datos.setLibre(Runtime.getRuntime().freeMemory());
        plantilla.convertAndSend("/push/reply-" + sessionId, datos);
        return datos;
    }
    
    @MessageExceptionHandler
    @SendToUser("/push/errores")
    public String handleException(Throwable excepcion)
    {
        return excepcion.getMessage();
    }
    
}