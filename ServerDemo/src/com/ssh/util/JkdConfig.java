package com.ssh.util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;  

 
//@XmlAccessorType(XmlAccessType.FIELD) 
//XML文件中的根标识  
  
//控制JAXB 绑定类中属性和字段的排序  
//@XmlType(propOrder = {   
//     "socketHostName",   
//     "socketPort"
//})  
 
 
@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "JkdConfig") 
@XmlType(propOrder = {   
"socketHostName",   
"socketPort"
}) 
public class JkdConfig    {
 
	 public JkdConfig(String socketHostName, String socketPort) {
		super();
		this.socketHostName = socketHostName;
		this.socketPort = socketPort;
	}
	public JkdConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
//	@XmlElement(name="socketHostName")  
	private  String socketHostName;
 //	@XmlElement(name="socketPort") 
	private   String socketPort;
	
 

	public String getSocketHostName() {
		return socketHostName;
	}
  
	public void setSocketHostName(String socketHostName) {
		this.socketHostName = socketHostName;
	}

	public String getSocketPort() {
		return socketPort;
	}
  
	public void setSocketPort(String socketPort) {
		this.socketPort = socketPort;
	}
	
	public static JkdConfig getJkdConfig()  {
		  URL url =  Thread.currentThread().getContextClassLoader().getResource("JkdConfig.xml");
		 // System.out.print("地址"+url.getPath());
		 JkdConfig jkdConfig=(JkdConfig)XMLUtil.convertXmlFileToObject(JkdConfig.class, url.getPath());
			 
		 return jkdConfig;
		}
	
}
