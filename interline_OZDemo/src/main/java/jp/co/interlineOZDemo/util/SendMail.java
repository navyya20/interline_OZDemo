package jp.co.interlineOZDemo.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	public void MailSend(String emailAddress ,String title,String text, String html ,String fileName) {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");  //로그인시 TLS를 사용할 것인지 설정
        prop.put("mail.smtp.host", "smtp.gmail.com");  //이메일 발송을 처리해줄 SMTP서버
        prop.put("mail.smtp.auth", "true");  //SMTP 서버의 인증을 사용한다는 의미
        prop.put("mail.smtp.port", "587");  //구글SMTP서버 TLS의 포트번호는 587이며 SSL의 포트번호는 465이다.
        
        
        Authenticator auth = new GetAuthen();   //Authenticator에대해서 알아볼 필요가있음. 꼭 상속받은 클레스로 객체를 만들어서 생성해야하는지.? 자체에서 ID PW 지정해서 인증객체를 생성할수는 없나?
        
        Session session = Session.getDefaultInstance(prop, auth);  //위정보를 합쳐서 session을 만듬.
        
        MimeMessage msg = new MimeMessage(session);  //MimeMessage는 Message(추상)클래스를 상속받은 인터넷 메일을 위한 클래스이다. 위에서 생성한 세션을 담아 msg 객체를 생성한다.
    
        try {
            msg.setSentDate(new Date());  //보내는 날짜 지정
            
            msg.setFrom(new InternetAddress("navyya20@gmail.com", "InterlineOZdemo")); //Message 클래스의 setFrom() 메소드를 사용하여 발송자를 지정한다. 발송자의 메일, 발송자명
            																	//InternetAddress 클래스는 이메일 주소를 나타날 때 사용하는 클래스이다.

            InternetAddress to = new InternetAddress(emailAddress);       //수신자의 메일을 생성한다. 
            msg.setRecipient(Message.RecipientType.TO, to);  //Message 클래스의 setRecipient() 메소드를 사용하여 수신자를 설정한다. setRecipient() 메소드로 수신자, 참조, 숨은 참조 설정이 가능하다.          
            msg.setSubject(title, "UTF-8");            
            msg.setText("안녕하세요 테스트 메일입니다.", "UTF-8");       
            
            //여기까지는 메일만 보내기
            
            //여기서 부턴 파일 첨부하기
            MimeBodyPart attachPart = new MimeBodyPart();   //마임바디파트(파일용) 생성 이걸 msg에 넣어서 보낼것임.
            FileDataSource fds = new FileDataSource("C:\\Apache Software Foundation\\Tomcat 8.5\\webapps\\ozsch80\\Repository\\pdf\\document.pdf");  //파일을 가져온다.파라메터로 경로 혹은 파일 오브젝트
            attachPart.setDataHandler(new DataHandler(fds));  // 마임바디파트에 넣는다.
            attachPart.setFileName("noname.pdf"); // 파일명
            
            MimeBodyPart bodypart = new MimeBodyPart();  //마임바디파트(내용용) 이것도 msg에 넣어서 보낼것임.
            bodypart.setContent(html, "text/html;charset=UTF-8"); 
           
            Multipart multipart = new MimeMultipart();  //파일 파트와  콘텐츠 파트를 핸들링
            multipart.addBodyPart(bodypart);
            multipart.addBodyPart(attachPart);
           
            msg.setContent(multipart);	//  msg에 setText랑 바디파트의 setcontents랑 안겹치나???===겹친다.  msg.setText는 지워지고 bodypart.setContent가 내용으로 들어간다.
            
            Transport.send(msg);
            
        } catch(AddressException ae) {            
            System.out.println("AddressException : " + ae.getMessage());           
        } catch(MessagingException me) {            
            System.out.println("MessagingException : " + me.getMessage());
        } catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException : " + e.getMessage());			
        }
                
    }

}
