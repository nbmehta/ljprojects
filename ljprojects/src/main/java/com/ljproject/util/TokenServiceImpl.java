package com.ljproject.util;

import java.util.Arrays;
import java.util.Calendar;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.ljproject.model.PasswordResetToken;
import com.ljproject.model.User;
import com.ljproject.repository.PasswordResetTokenRepository;



@Service("tokenService")
public class TokenServiceImpl implements TokenService{
	
	 public static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
	
     @Autowired
	 MailService mailService;
	
	 @Autowired
	 JavaMailSender mailSender;
		 
	 @Autowired
	 OtpService otpService;

	@Override
	public String sendRestLink(User user, String token) {

		try {
			PasswordResetToken ps = new PasswordResetToken();
			ps.setToken(token);

			MimeMessagePreparator preparator = getContentWtihAttachementMessagePreparator(user, ps);
			mailSender.send(preparator);
			logger.info("Reset link has been sent.............................");

		} catch (MailException ex) {
			logger.info(ex.getMessage());
		}

		return token;

	}

	private MimeMessagePreparator getContentWtihAttachementMessagePreparator(User user, PasswordResetToken ps) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				String url = "http://localhost:8080" + "/reset/changePassword?id=" + user.getId() + "&token="
						+ ps.getToken();

				helper.setSubject("Reset link has been sent.............................");
				helper.setFrom("mehtanitesh786@gmail.com");
				helper.setTo(user.getEmail());

				String content = "Dear " + user.getFirstName() + ", Reset your password " + url + ".";

				helper.setText(content);

			}
		};
		return preparator;
	}

	@Override
	public String validatePasswordResetToken(long id, String token) {
		PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
		if ((passToken == null) || (passToken.getUser().getId() != id)) {
			return "invalidToken";
		}

		Calendar cal = Calendar.getInstance();
		if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return "expired";
		}

		User user = passToken.getUser();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return null;
	}

}
