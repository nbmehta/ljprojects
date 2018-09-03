package com.ljproject.controller;

import java.util.UUID;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.ljproject.dto.ChangePasswordDto;
import com.ljproject.dto.PasswordForgotDto;
import com.ljproject.model.PasswordResetToken;
import com.ljproject.model.User;
import com.ljproject.repository.PasswordResetTokenRepository;
import com.ljproject.service.UserService;
import com.ljproject.util.MailService;
import com.ljproject.util.OtpService;
import com.ljproject.util.TokenService;


@Controller
public class LoginController {
	
	 public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@Autowired
	private TokenService tokenService;

	
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
	public String homePage() {

		return "home";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
			return "dashboard";
	}
	
	 @RequestMapping(value = { "/admin/delete/{id}" }, method = RequestMethod.GET)
	    public String deleteUser(@PathVariable long id) {
	        userService.deleteUserById(id);
	        return "redirect:/admin";
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
		model.addAttribute("PasswordForgotDto", new PasswordForgotDto());
		return "forgotPassword";
	}

	@RequestMapping(value = "/reset/changePassword", method = RequestMethod.GET)
	public String changePassword(@RequestParam(required = false) String token, @RequestParam("id") long id,
			Model model) {
		model.addAttribute("changePassword", new ChangePasswordDto());
		String reStringsult = tokenService.validatePasswordResetToken(id, token);
		model.addAttribute("token", token);
		model.addAttribute("userid", id);
		if (reStringsult == null) {

			return "resetPassword";
		}

		return reStringsult;
	}

	@RequestMapping(value = { "/admin/updatePassword", "/user/updatePassword" }, method = RequestMethod.GET)
	public String updatePassword(@RequestParam(required = false) String token, Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		logger.info(loggedInUser.getName());
		model.addAttribute("updatePassword", new ChangePasswordDto());
		return "updatePassword";
	}

	@RequestMapping(value = { "/admin/updatePassword", "/user/updatePassword" }, method = RequestMethod.POST)
	public String updatePass(@RequestParam(required = false) String token, Model model,
			@ModelAttribute("updatePassword") ChangePasswordDto changePasswordDto) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		logger.info("Thsi is testing api ------------------------------------");
		logger.info(loggedInUser.getName());
		return "updatePassword";
	}

	@RequestMapping(value = "/reset/submit", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute("changePassword") ChangePasswordDto changePasswordDto,
			@RequestParam("token") String token, @RequestParam("userId") long userId) {
		PasswordResetToken ps = passwordResetTokenRepository.findByToken(token);

		User user = ps.getUser();
		user.setPassword(changePasswordDto.getPassword());


		if (changePasswordDto.getPassword().equals(changePasswordDto.getConfirmpasword())) {
			userService.saveUser(user);
		}
		logger.info("save successfully ");
		return "resetPassword";
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String reset(@ModelAttribute("PasswordForgotDto") PasswordForgotDto passwordForgotDto)
			throws UserNotFoundException {
		User user = userService.findUserByEmail(passwordForgotDto.getEmail());
		if (user == null) {
			throw new UserNotFoundException();
		}
		String token = UUID.randomUUID().toString();
		logger.info(passwordForgotDto.getEmail());
		PasswordResetToken ps = new PasswordResetToken();
		ps.setUser(user);
		ps.setExpiryDate(1);
		ps.setToken(token);

		tokenService.sendRestLink(user, token);

		passwordResetTokenRepository.save(ps);
		return "forgotPassword";
	}
	
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

			char[] otp = otpService.genrateOtp();
			String convertedOtp = new String(otp);
			user.setOtp(convertedOtp);
			userService.saveUser(user);
			
			userService.sendEmailforApprove(user);
			mailService.sendEmail(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

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
