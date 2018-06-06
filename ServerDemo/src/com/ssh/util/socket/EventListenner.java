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
       System.out.println("建立连接");
    }
 
    @OnEvent("Register")
    public void onSync(SocketIOClient client, ClientObject clientObj) {
       // System.out.printf("收到消息-from: %s to:%s\n"+pusher.getMessage(), pusher.getPusherrid(),pusher.getReceiverid());
       clientCache.addClient(client, clientObj);
       // SocketIOClient ioClients = clientCache.getClient(pusher.getReceiverid());
       // System.out.println("clientCache");
//        if (ioClients == null) {
//            System.out.println("你发送消息的用户不在线");
//            return;
//        }
        client.sendEvent("receive_msg", clientObj+"成功建立连接");
    }
    
    @OnEvent("Send")
    public void onSend(SocketIOClient client,ChatObject chatObject) {
       // System.out.printf("收到消息-from: %s to:%s\n"+pusher.getMessage(), pusher.getPusherrid(),pusher.getReceiverid());    
       SocketIOClient sendClient=clientCache.getClient(chatObject.getReceiveid());
        if(sendClient==null)
        {
        	client.sendEvent("receive_msg",  "找不到此用户"+chatObject.getReceiveid());
        }
        else {			
            sendClient.sendEvent("receive_msg", chatObject.getPushid()+"向"+chatObject.getReceiveid()+"说："+chatObject.getMessage());
		}
 
    }
    
    public void webonSend(String receiveid,String message,String pushid) {
        // System.out.printf("收到消息-from: %s to:%s\n"+pusher.getMessage(), pusher.getPusherrid(),pusher.getReceiverid());
     
        SocketIOClient sendClient=clientCache.getClient(receiveid);
        if(sendClient!=null)
        {
        	 sendClient.sendEvent("receive_msg", pushid+"向"+receiveid+"说："+message);
        }
        
     }
 
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("关闭连接");
    }

	 
}