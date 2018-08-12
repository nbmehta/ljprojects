package com.ljproject.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.ljproject.model.User;



@Service("mailService")
public class MailServiceImpl implements MailService {

	 @Autowired
	 JavaMailSender mailSender;
		 
	 @Autowired
	 OtpService otpService;
	
	@Override
	public void sendEmail(Object object) {
		
		User user=(User)object;
		
		MimeMessagePreparator preparator = getContentWtihAttachementMessagePreparator(user);
		 
        try {
            mailSender.send(preparator);
            System.out.println("Otp has been sent.............................");
          
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
		
	}
	
	

	private MimeMessagePreparator getContentWtihAttachementMessagePreparator(final User user) {
		 
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {
	 
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	 
	                helper.setSubject("Otp has been sent.............................");
	                helper.setFrom("bhagavatibhai93@gmail.com");
	                helper.setTo(user.getEmail());
	                
	                String content = "Dear " + user.getFirstName()
	                        + ", thank you for Registration "+"your otp is "+ user.getOtp() +".";
	 
	                helper.setText(content);
	 
	                
	 
	            }
	        };
	        return preparator;
	    }


	 
	

}
