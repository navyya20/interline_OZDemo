package jp.co.interlineOZDemo.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.springframework.core.io.ClassPathResource;


public class GetProperties {
	
	String webIP="";
	String ozIP="";
	String serverPath="";
	String serverPath2="";
	
	public GetProperties() {
		ClassPathResource resource = new ClassPathResource("user.properties");
		
		
		Properties properties = new Properties();
	   
	    try {
		    properties.load(resource.getInputStream());
		} catch (Exception e) {
			System.out.println(e);
		}
	    webIP=properties.getProperty("webIP","localhost:8888");
	    ozIP=properties.getProperty("ozIP","localhost:8888");
	    serverPath=properties.getProperty("serverPath","/usr/local/lib/apache-tomcat-8.5.57");
	    serverPath2=properties.getProperty("serverPath2","C:/Apache Software Foundation/Tomcat 8.5");
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
	
	
	public String getServerPath2() {
		return serverPath2;
	}
	public void setServerPath2(String serverPath2) {
		this.serverPath2 = serverPath2;
	}


	@Override
	public String toString() {
		return "getProperties [webIP=" + webIP + ", ozIP=" + ozIP + "]";
	}


	public String getOZViewerSource(String device) {
		
		String sources = "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=no\"/>\r\n" + 
				"<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css\"/>\r\n" + 
				"<script src=\"http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js\"></script>\r\n" + 
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog.min.css\"/>\r\n" + 
				"<script type=\"text/javascript\" src=\"http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog2.min.js\"></script>\r\n" + 
				"<script src=\"http://"+this.ozIP+"/oz80/ozhviewer/jquery.mouseSwipe.js\" type=\"text/javascript\"></script>";		
		if( device.equals("PC") ) {
			return sources;
		}else {
			return sources;			
		}
	}


	
	    
	
}
