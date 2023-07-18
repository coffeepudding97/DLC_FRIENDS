package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailSender {
	private MailSender() {}
	private static MailSender instance = new MailSender();
	public static MailSender getInstance() {
		return instance;
	}
	
	public String gmailSend(String recipient) {
		String code = null;
        String user = "dlcfriendscheck@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = "lnqiuychxqsqixye";   // 패스워드

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); 
        //465	
        prop.put("mail.smtp.port", 465); 
        prop.put("mail.smtp.auth", "true"); 
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 

            // Subject
            message.setSubject("DLC:Friends 회원 확인을 위한 인증번호 입니다."); //메일 제목을 입력

            // Text
            code = getVerificationCode();
            message.setText("인증번호 : " + code);    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return code;
    }
	
	private String getVerificationCode() {
		String code = "";
		
		for(int i=0;i<6;i++) {
			code += String.valueOf((int)(Math.random()*10));
		}
		
		return code;
	}
}
