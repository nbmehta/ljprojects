package com.ljproject.util;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import com.ljproject.model.User;



@Service("mailService")
public class MailServiceImpl implements MailService {
	 public static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

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
            logger.info("Otp has been sent.............................");
        } catch (MailException ex) {
        	logger.info(ex.getMessage());
        }
	}
	
	

	private MimeMessagePreparator getContentWtihAttachementMessagePreparator(final User user) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setSubject("Otp has been sent.............................");
				helper.setFrom("bhagavatibhai93@gmail.com");
				helper.setTo(user.getEmail());
				String content = "Dear " + user.getFirstName() + ", thank you for Registration " + "your otp is "
						+ user.getOtp() + ".";
				helper.setText(content);

			}
		};
		return preparator;
	}

}
