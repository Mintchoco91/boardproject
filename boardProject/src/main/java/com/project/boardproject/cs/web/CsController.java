
package com.project.boardproject.cs.web;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.boardproject.cs.service.CsSendMailVO;
import com.project.boardproject.cs.service.CsService;

/*
 * 파일명 : CsController.java
 * 용도    : 고객센터 관련 컨트롤러
 * 작성자 : mintchoco91
 * 변경일 : 2019/11/16
 */

@Controller
public class CsController {

	@Autowired
	private CsService csService;

	private static final Logger logger = LoggerFactory.getLogger(CsController.class);

	//고객센터 메일보내는 페이지 메인	
	@RequestMapping("csRegister")
	public String index(Model model) {
		return "cs/csRegister";
	}
	

	//메일전송
	@RequestMapping("csSendMail")
	public String csSendMail(Model model, CsSendMailVO csSendMailVO) {	        
			String emailAddr = csSendMailVO.getEmail();
	        String subject = csSendMailVO.getSubject();
	        String contents = csSendMailVO.getContents();
			
			String host = "smtp.cafe24.com";
	        String user = "mintchoco91@mintchoco91.cafe24.com"; //
	        String password = "rldnjs01!@";   // 패스워드
	        
	        // SMTP 서버 정보를 설정한다.
	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", host); 
	        prop.put("mail.smtp.port", 587); 
	        prop.put("mail.smtp.auth", "true"); 
	        
	        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, password);
	            }
	        });

	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(user));

	            //EmailAddr & Subject & Contents Setting 
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddr)); 
	            message.setSubject(subject); 
	            message.setText(contents);

	            // send the message
	            Transport.send(message);
	            System.out.println("message sent successfully...");
	        } catch (AddressException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("AddressException Err : "+ e.getMessage().toString());
	        } catch (MessagingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("MessagingException Err : "+ e.getMessage().toString());
	        }

		return "cs/csRegister";
	}

}