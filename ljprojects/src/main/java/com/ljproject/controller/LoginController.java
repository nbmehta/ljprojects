package com.ljproject.controller;

import java.time.Instant;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ljproject.dto.ChangePasswordDto;
import com.ljproject.dto.PasswordForgotDto;
import com.ljproject.model.PasswordResetToken;
import com.ljproject.model.Role;
import com.ljproject.model.User;
import com.ljproject.repository.PasswordResetTokenRepository;
import com.ljproject.repository.RoleRepository;
import com.ljproject.repository.UserRepository;
import com.ljproject.service.UserService;
import com.ljproject.util.MailService;
import com.ljproject.util.OtpService;
import com.ljproject.util.TokenService;

import antlr.collections.List;

@Controller
public class LoginController {				

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	OtpService otpService;
	
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "login";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		java.util.List<User> listuser=userService.listUser();
		model.addAttribute("listuser", listuser);
		return "admin";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		
		return "welcome";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model) {
		model.addAttribute("PasswordForgotDto",new PasswordForgotDto());
		return "forgotPassword";
	}
	
	
	@RequestMapping(value = "/reset/changePassword", method = RequestMethod.GET)
	public String changePassword(@RequestParam(required = false) String token,@RequestParam("id") long id,Model model) {
		
		System.out.println(token);
		model.addAttribute("changePassword", new ChangePasswordDto());
		String reStringsult=tokenService.validatePasswordResetToken(id, token);
		System.out.println("testing demo"+reStringsult);
		model.addAttribute("token", token);
		model.addAttribute("userid", id);
		if(reStringsult==null)
		{
			
			return "resetPassword";
		}
		
		
		
		return reStringsult;
	}
	
	
	
	
	@RequestMapping(value = {"/admin/updatePassword","/user/updatePassword"}, method = RequestMethod.GET)
	public String updatePassword(@RequestParam(required = false) String token,Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		PasswordResetToken user2=passwordResetTokenRepository.findByToken(token);
		System.out.println(loggedInUser.getName());
		model.addAttribute("updatePassword", new ChangePasswordDto());
		return "updatePassword";
	}
	
	
	@RequestMapping(value = {"/admin/updatePassword","/user/updatePassword"}, method = RequestMethod.POST)
	public String updatePass(@RequestParam(required = false) String token,Model model,@ModelAttribute("updatePassword") ChangePasswordDto changePasswordDto ) {
		
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("Thsi is testing api ------------------------------------");
		System.out.println(loggedInUser.getName());
		User user=userService.findUserByEmail(loggedInUser.getName());
		System.out.println(changePasswordDto.getOldPassword() + changePasswordDto.getConfirmpasword());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		encoder.matches(changePasswordDto.getOldPassword(), user.getPassword()); 
		
		
		if(user.getPassword().equals(changePasswordDto.getOldPassword())&& changePasswordDto.getPassword().equals(changePasswordDto.getConfirmpasword()))
		{
			
			System.out.println("insite if loop");
		}	
		
		
		
		
		return "updatePassword";
	}
	
	
	
	@RequestMapping(value = "/reset/submit", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute("changePassword") ChangePasswordDto changePasswordDto,@RequestParam("token")String token,@RequestParam("userId")long userId) {
		
	System.out.println("this is test "+token);	
	PasswordResetToken ps= passwordResetTokenRepository.findByToken(token);
	
	User user=ps.getUser();
	user.setPassword(changePasswordDto.getPassword());
	
	System.out.println("this is testing password"+user.getPassword());
	if(changePasswordDto.getPassword().equals(changePasswordDto.getConfirmpasword()))
	{	
	userService.saveUser(user);
	}
	System.out.println("save successfully ");
	
	System.out.println("this is test "+userId);	
		return "resetPassword";
	}
	
	
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String reset( @ModelAttribute("PasswordForgotDto")PasswordForgotDto passwordForgotDto) throws UserNotFoundException {
		User user = userService.findUserByEmail(passwordForgotDto.getEmail());
	    if (user == null) {
	        throw new UserNotFoundException();
	    }
	    String token = UUID.randomUUID().toString();
		System.out.println(passwordForgotDto.getEmail());
		PasswordResetToken ps= new PasswordResetToken();
		ps.setUser(user);
		ps.setExpiryDate(1);
		ps.setToken(token);
		
		tokenService.sendRestLink(user, token);
			
		passwordResetTokenRepository.save(ps);
		return "forgotPassword";
	}
	
	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * loginPage() { return "login"; }
	 */
	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * login(Model model, String error, String logout) { if (error != null)
	 * model.addAttribute("error", "Your username and password is invalid.");
	 * 
	 * if (logout != null) model.addAttribute("message",
	 * "You have been logged out successfully.");
	 * 
	 * return "login"; }
	 */

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			
			
			char[] otp=otpService.genrateOtp();
            String convertedOtp=new String(otp);
            user.setOtp(convertedOtp);
            user.setCreatedOn(LocalTime.now());
    		user.setLastLogin(LocalTime.now());;
			userService.saveUser(user);
			Role role=roleRepository.findOne(user.getId());
			
			userService.sendEmailforApprove(user);;
			
			System.out.println("Role user ===>"+user.getRoles().toString());
			mailService.sendEmail(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	/*
	 * @RequestMapping(value="/admin", method = RequestMethod.GET) public
	 * ModelAndView home(){ ModelAndView modelAndView = new ModelAndView();
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * User user = userService.findUserByEmail(auth.getName());
	 * modelAndView.addObject("userName", "Welcome " + user.getName() + " " +
	 * user.getLastName() + " (" + user.getEmail() + ")"); modelAndView.addObject(
	 * "adminMessage","Content Available Only for Users with Admin Role");
	 * modelAndView.setViewName("welcome"); return modelAndView; }
	 */

	/*
	 * @RequestMapping(value="/user", method = RequestMethod.GET) public
	 * ModelAndView homeuser(){ ModelAndView modelAndView = new ModelAndView();
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * User user = userService.findUserByEmail(auth.getName());
	 * modelAndView.addObject("userName", "Welcome " + user.getName() + " " +
	 * user.getLastName() + " (" + user.getEmail() + ")"); modelAndView.addObject(
	 * "adminMessage","Content Available Only for Users with USER Role");
	 * modelAndView.setViewName("user/home"); return modelAndView; }
	 */

	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/views/test");
		return modelAndView;
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}