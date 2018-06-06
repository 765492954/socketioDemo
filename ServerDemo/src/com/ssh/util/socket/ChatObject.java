package com.ssh.util.socket;

public class ChatObject {
	   private String receiveid;
	   private String message;
	   private String pushid;
	public String getReceiveid() {
		return receiveid;
	}
	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPushid() {
		return pushid;
	}
	public void setPushid(String pushid) {
		this.pushid = pushid;
	}
	@Override
	public String toString() {
		return "ChatObject [receiveid=" + receiveid + ", message=" + message + ", pushid=" + pushid + "]";
	}
	   

}
