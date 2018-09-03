package com.ljproject.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("OtpService")
public class OtpServiceImpl implements OtpService {
	
	 public static final Logger logger = LoggerFactory.getLogger(OtpServiceImpl.class);

	 @Autowired
	 JavaMailSender mailSender;
 
	 @Autowired
	 OtpService otpService;
	
	 @Override
	 public char[] genrateOtp() {
		logger.info("Generating OTP using random() : ");
		logger.info("You OTP is : ");

		// Using numeric values
		String numbers = "0123456789";

		// Using random method
		Random rndm_method = new Random();

		char[] otp = new char[4];

		for (int i = 0; i < 4; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		return otp;
	}
}
