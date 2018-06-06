package com.ssh.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLUtil {  
    /**    
     * ������ֱ��ת����String���͵� XML���    
     *     
     * @param obj    
     * @return    
     */      
    public static String convertToXml(Object obj) {      
        // ���������      
        StringWriter sw = new StringWriter();      
        try {      
            // ����jdk���Դ���ת����ʵ��      
            JAXBContext context = JAXBContext.newInstance(obj.getClass());      
      
            Marshaller marshaller = context.createMarshaller();      
            // ��ʽ��xml����ĸ�ʽ      
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,      
                    Boolean.TRUE);      
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");   
            // ������ת�����������ʽ��xml      
            marshaller.marshal(obj, sw);      
        } catch (JAXBException e) {      
            e.printStackTrace();      
        }      
        return sw.toString();      
    }      
      
    /**    
     * ���������·��д��ָ����xml�ļ��� 
     *     
     * @param obj    
     * @param path    
     * @return    
     */      
    public static void convertToXml(Object obj, String path) {      
        try {      
            // ����jdk���Դ���ת����ʵ��      
            JAXBContext context = JAXBContext.newInstance(obj.getClass());      
      
            Marshaller marshaller = context.createMarshaller();      
            // ��ʽ��xml����ĸ�ʽ      
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,      
                    Boolean.TRUE);      
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
            // ������ת�����������ʽ��xml      
            // ���������      
            FileWriter fw = null;      
            try {      
                fw = new FileWriter(path);      
            } catch (IOException e) {      
                e.printStackTrace();      
            }      
            marshaller.marshal(obj, fw);      
        } catch (JAXBException e) {      
            e.printStackTrace();      
        }      
    }      
      
    /**    
     * ��String���͵�xmlת���ɶ���    
     */      
    public static Object convertXmlStrToObject(Class<?> clazz, String xmlStr) {      
        Object xmlObject = null;      
        try {  
            JAXBContext context = JAXBContext.newInstance(clazz); 
            
            // ���н�Xmlת�ɶ���ĺ��Ľӿ�      
            Unmarshaller unmarshal = context.createUnmarshaller();  
            StringReader sr = new StringReader(xmlStr);   
             
            xmlObject = unmarshal.unmarshal(sr);  
        } catch (Exception e) {  
            e.printStackTrace();      
        }      
        return xmlObject;      
    }      
      
    /**    
     * ��file���͵�xmlת���ɶ���    
     */      
    public static Object convertXmlFileToObject(Class<?> clazz, String xmlPath) {      
        Object xmlObject = null;      
        try {  
            JAXBContext context = JAXBContext.newInstance(clazz);      
            Unmarshaller unmarshaller = context.createUnmarshaller();        
            InputStreamReader isr=new InputStreamReader(new FileInputStream(xmlPath),"UTF-8"); 
      
            xmlObject = unmarshaller.unmarshal(isr);      
        } catch (Exception e) {      
            e.printStackTrace();      
        }      
        return xmlObject;      
    }   
    
 
}  