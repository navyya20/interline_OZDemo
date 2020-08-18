package jp.co.interlineOZDemo.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GetAuthen extends Authenticator{
	PasswordAuthentication pa;
    
    public GetAuthen() {
        String mail_id = "navyya20";
        String mail_pw = "eodms1614";
        
        pa = new PasswordAuthentication(mail_id, mail_pw);
    }
    
    public PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }

}
