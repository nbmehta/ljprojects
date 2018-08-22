package com.ljproject.service;

import java.util.Arrays;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ljproject.LjprojectApplication;
import com.ljproject.model.Role;
import com.ljproject.model.User;
import com.ljproject.repository.RoleRepository;
import com.ljproject.repository.UserRepository;



@Service("userService")
public class UserServiceImpl implements UserService{
	 public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    JavaMailSender mailSender;
    
   
    
 
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
         Role userRole = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	public void sendEmailforApprove(User user) {
	User user1=user;
		
		MimeMessagePreparator preparator = getContentWtihAttachementMessagePreparator(user1);
		 
        try {
            mailSender.send(preparator);
            logger.info("Otp has been sent.............................");
          
        } catch (MailException ex) {
        	logger.info(ex.getMessage());
        }
	
		
	}

	private MimeMessagePreparator getContentWtihAttachementMessagePreparator(User user1) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			 
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                
                
				String url = "http://localhost:8080" + "/approve/user?id=" + 
                	      user1.getId() ;
 
                helper.setSubject("Reset link has been sent.............................");
                helper.setFrom("mehtanitesh786@gmail.com");
                helper.setTo("mehtanitesh786@gmail.com");
                
                String content = "Dear " + user1.getFirstName()
                        + ", Approve account " + url +".";
 
                helper.setText(content);
 
                
 
            }
        };
        return preparator;

	}

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		List<User> listuser=userRepository.findAll();
		return listuser;
	}

	/* (non-Javadoc)
	 * @see com.ljproject.service.UserService#deleteUserById(java.lang.Long)
	 */
	@Override
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub
		userRepository.delete(id);
	}

	

}
