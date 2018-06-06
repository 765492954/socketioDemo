package com.ssh.util.socket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

 
import org.springframework.stereotype.Service;
import com.corundumstudio.socketio.SocketIOClient;

@Service("clientCache")
public class SocketIOClientCache {
    //String：EventType类型
    private static Map<String,SocketIOClient> clients=new ConcurrentHashMap<String,SocketIOClient>();
 
    public Map<String, SocketIOClient> getClients() {
		return clients;
	}

	public void setClients(Map<String, SocketIOClient> clients) {
		this.clients = clients;
	}

	//用户发送消息添加
    public void addClient(SocketIOClient client,ClientObject clientObj){
        clients.put(clientObj.getUserid(),client);
    }
 
    //用户退出时移除
    public void remove(ClientObject pushSocket) {
        clients.remove(pushSocket.getUserid());
    }
     
    //获取所有
    public  SocketIOClient getClient(String to) {
    	System.out.print(clients);
        return clients.get(to);
    }
}