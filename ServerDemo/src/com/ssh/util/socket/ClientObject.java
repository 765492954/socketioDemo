package com.ssh.util.socket;

//ChatObject
public class ClientObject {
    private String userid;
    private String message;
 
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "ClientObject [userid=" + userid + ", message=" + message + "]";
	}
 
 
 
}
