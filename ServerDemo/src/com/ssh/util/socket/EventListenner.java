package com.ssh.util.socket;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;

@Service("eventListenner")
public class EventListenner {
    @Resource(name = "clientCache")
    private SocketIOClientCache clientCache;
 
 
    public SocketIOClientCache getClientCache() {
		return clientCache;
	}

	public void setClientCache(SocketIOClientCache clientCache) {
		this.clientCache = clientCache;
	}

	@OnConnect
    public void onConnect(SocketIOClient client) {
       System.out.println("��������");
    }
 
    @OnEvent("Register")
    public void onSync(SocketIOClient client, ClientObject clientObj) {
       // System.out.printf("�յ���Ϣ-from: %s to:%s\n"+pusher.getMessage(), pusher.getPusherrid(),pusher.getReceiverid());
       clientCache.addClient(client, clientObj);
       // SocketIOClient ioClients = clientCache.getClient(pusher.getReceiverid());
       // System.out.println("clientCache");
//        if (ioClients == null) {
//            System.out.println("�㷢����Ϣ���û�������");
//            return;
//        }
        client.sendEvent("receive_msg", clientObj+"�ɹ���������");
    }
    
    @OnEvent("Send")
    public void onSend(SocketIOClient client,ChatObject chatObject) {
       // System.out.printf("�յ���Ϣ-from: %s to:%s\n"+pusher.getMessage(), pusher.getPusherrid(),pusher.getReceiverid());    
       SocketIOClient sendClient=clientCache.getClient(chatObject.getReceiveid());
        if(sendClient==null)
        {
        	client.sendEvent("receive_msg",  "�Ҳ������û�"+chatObject.getReceiveid());
        }
        else {			
            sendClient.sendEvent("receive_msg", chatObject.getPushid()+"��"+chatObject.getReceiveid()+"˵��"+chatObject.getMessage());
		}
 
    }
    
    public void webonSend(String receiveid,String message,String pushid) {
        // System.out.printf("�յ���Ϣ-from: %s to:%s\n"+pusher.getMessage(), pusher.getPusherrid(),pusher.getReceiverid());
     
        SocketIOClient sendClient=clientCache.getClient(receiveid);
        if(sendClient!=null)
        {
        	 sendClient.sendEvent("receive_msg", pushid+"��"+receiveid+"˵��"+message);
        }
        
     }
 
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("�ر�����");
    }

	 
}