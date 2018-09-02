package com.ljproject.web.services.restcontroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ljproject.model.User;
import com.ljproject.repository.RoleRepository;
import com.ljproject.repository.UserRepository;
import com.ljproject.security.JwtTokenProvider;
import com.ljproject.util.MailService;
import com.ljproject.util.OtpService;
import com.ljproject.web.services.payload.ApiResponse;
import com.ljproject.web.services.payload.JwtAuthenticationResponse;
import com.ljproject.web.services.payload.LoginRequest;
import com.ljproject.web.services.payload.SignUpRequest;

import javax.validation.Valid;
import java.net.URI;


/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
	private MailService mailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
 
    @Autowired
	OtpService otpService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.findByUsername(signUpRequest.getUsername()) == null) {
			@SuppressWarnings({})
			ResponseEntity responseEntity = new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
					HttpStatus.BAD_REQUEST);
			return responseEntity;
		}

		if (userRepository.findByEmail(signUpRequest.getEmail()) != null) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());
		user.setLastName("test");

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		char[] otp = otpService.genrateOtp();
		String convertedOtp = new String(otp);
		user.setOtp(convertedOtp);

		User result = userRepository.save(user);

		mailService.sendEmail(user);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
}
