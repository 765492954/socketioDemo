package com.ssh.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssh.service.impl.SocketIoService;
 
import com.ssh.util.socket.ClientObject;

@Controller
public class SocketIoServer {
	@Resource(name="socketIoService")
	private SocketIoService serviceIo;
    @RequestMapping(value = "startServer")
    public void startServer(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "*");
       
        try {
            if (SocketIoService.getServer() == null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                        	  System.out.print("启动");
                            serviceIo.startServer(); 
                             
                        } catch (InterruptedException e) {
                             
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
          
        }
    }
    
    @RequestMapping(value = "PushServer")
    public void PushServer(HttpServletRequest  req,HttpServletResponse  response) {
      response.setHeader("Access-Control-Allow-Credentials", "true");
         response.setHeader("Access-Control-Allow-Origin", "*");
  
        try {
            if (SocketIoService.getServer() == null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                        	  System.out.print("启动");
                            serviceIo.startServer();
                            System.out.print("启动");
                             
                        } catch (InterruptedException e) {
                             
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
          
        }
        String message = req.getParameter("message");  
        String pusherrid = req.getParameter("pusherrid");  
         String receiverid = req.getParameter("receiverid");         
        System.out.print("进入发送");
        serviceIo.PushServer( receiverid,message,pusherrid);
        
    }
}
