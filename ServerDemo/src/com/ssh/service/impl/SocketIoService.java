package com.ssh.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import java.util.List;

import org.apache.commons.validator.Var;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.connection.CciLocalTransactionManager;
import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.ssh.util.JkdConfig;
import com.ssh.util.socket.EventListenner;
import com.ssh.util.socket.ClientObject;
 

 

@Service("socketIoService")
public class SocketIoService {
    static SocketIOServer server;
 
    
    @Resource(name="eventListenner")
    private EventListenner eventListenner;

    //��������
    public void startServer()throws InterruptedException{
 
        Configuration config = new Configuration();
        JkdConfig jkdconfig=JkdConfig.getJkdConfig();
		//����������ip
  
        String hostName = jkdconfig.getSocketHostName();  ;
        config.setHostname(hostName);
        //�˿�
        int socketPort = Integer.parseInt(jkdconfig.getSocketPort()) ;
        config.setPort(socketPort);
        System.out.print(hostName+socketPort);
 
        config.setMaxFramePayloadLength(1024 * 1024);
        config.setMaxHttpContentLength(1024 * 1024);
        server = new SocketIOServer(config);

        //��ӿͻ��������¼�
        server.addListeners(eventListenner);
        
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }


    /**
     *  ���������ӿͻ���������Ϣ
     * @param eventType ���͵��¼�����
     * @param message  ���͵�����
     */
    public void sendMessageToAllClient(String eventType,String message){
        Collection<SocketIOClient> clients = server.getAllClients();
 
        
        for(SocketIOClient client: clients){
            client.sendEvent(eventType,message);
        }
    }

    
    /**
     *  �������ͻ���������Ϣ
     * @param eventType ���͵��¼�����
     * @param message  ���͵�����
     */
    public void sendMessageToClient(UUID uuid,String eventType,String message){
    	SocketIOClient clients = server.getClient(uuid);
     
    	clients.sendEvent(eventType,message);
         
    }
    /**
     * ֹͣ����
     */
    public void stopServer(){
        if(server != null){
            server.stop();
            server = null;
        }
    }

    public static SocketIOServer getServer() {
    	
    	  
        return server;
    }
    
    
    public  void PushServer(String receiveid,String message,String pushid) {
    	  
    	eventListenner.webonSend( receiveid, message, pushid);        
    }
    
    //ע������¼�
    /*
    public  void addServiceUser(SocketIOServer server) {
    	 server.addEventListener("register", PushSocket.class, new DataListener<PushSocket>() {
				@Override
				public void onData(SocketIOClient client, PushSocket data, AckRequest ackSender) throws Exception {
					 					 
				}
    	    });
	}
    
    public void startServer2()throws InterruptedException{
    	 
        Configuration config = new Configuration();
        JkdConfig jkdconfig=JkdConfig.getJkdConfig();
		//����������ip
  
        String hostName = jkdconfig.getSocketHostName();  ;
        config.setHostname(hostName);
        //�˿�
        int socketPort = Integer.parseInt(jkdconfig.getSocketPort()) ;
        config.setPort(socketPort);
        System.out.print(hostName+socketPort);
 
        config.setMaxFramePayloadLength(1024 * 1024);
        config.setMaxHttpContentLength(1024 * 1024);
        server = new SocketIOServer(config);

        //��ӿͻ��������¼�
        server.addConnectListener(new ConnectListener() {
           
			@Override
            public void onConnect(SocketIOClient client) {
                String sa = client.getRemoteAddress().toString();
                String clientIp = sa.substring(1,sa.indexOf(":"));//��ȡ�豸ip
                 System.out.println(clientIp+"-------------------------"+"�ͻ���������"); 
                Map params = client.getHandshakeData().getUrlParams();
 
                //��ȡ�ͻ������ӵ�uuid����
                Object object = params.get("uuid");
                String uuid = "";
                if(object != null){
                    uuid = ((List<String>)object).get(0);
                    //��uuid�����ӿͻ��˶�����а�
                    clientsMap.put(uuid,client);
                }
                //���ͻ��˷�����Ϣ
                client.sendEvent("connect_msg",clientIp+"�ͻ�����ã����Ƿ���ˣ��ܰ�������");
            }
        });
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }
    */

   
    
}