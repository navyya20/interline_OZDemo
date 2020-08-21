package jp.co.interlineOZDemo.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.springframework.core.io.ClassPathResource;


public class GetProperties {
	
	String webIP="";
	String ozIP="";
	String serverPath="";
	
	public GetProperties() {
		ClassPathResource resource = new ClassPathResource("db2.properties");
		
		
		Properties properties = new Properties();
	   
	    try {
		    properties.load(resource.getInputStream());
		} catch (Exception e) {
			System.out.println(e);
		}
	    webIP=properties.getProperty("webIP","localhost:8888");
	    ozIP=properties.getProperty("ozIP","localhost:8888");
	    serverPath=properties.getProperty("ozIP","C:\\Apache Software Foundation\\Tomcat 8.5");
	}


	public String getWebIP() {
		return webIP;
	}


	public void setWebIP(String webIP) {
		this.webIP = webIP;
	}


	public String getOzIP() {
		return ozIP;
	}


	public void setOzIP(String ozIP) {
		this.ozIP = ozIP;
	}


	public String getServerPath() {
		return serverPath;
	}


	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}


	@Override
	public String toString() {
		return "getProperties [webIP=" + webIP + ", ozIP=" + ozIP + "]";
	}


	


	
	    
	
}
