/**
 * 
 */
package com.ljproject.controller;

import java.io.IOException;
import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ljproject.model.FileBucket;
import com.ljproject.model.User;
import com.ljproject.model.UserProfile;
import com.ljproject.service.UserProfileService;
import com.ljproject.service.UserService;

/**
 * @author Nitesh
 *
 */
@Controller
public class UserController {
	
	 public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	 
	 
	 @Autowired
	 UserService userService;
	 
	 @Autowired
	 UserProfileService userProfileService;
	 
	 
	 
	 @RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.POST)
	    public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int userId) throws IOException{
	         
	        if (result.hasErrors()) {
	        	logger.info("validation errors");
	            User user = userService.findById(userId);
	            model.addAttribute("user", user);
	 
	            List<UserProfile> documents = userProfileService.findAllByUserId(userId);
	            model.addAttribute("documents", documents);
	             
	            return "managedocuments";
	        } else {
	             
	        	logger.info("Fetching file");
	             
	            User user = userService.findById(userId);
	            model.addAttribute("user", user);
	            model.addAttribute("userId", userId);
	 
	            saveDocument(fileBucket, user);
	 
	            return "redirect:/add-document-"+userId;
	        }
	    }
	 
	@ResponseBody
	@RequestMapping(value = { "/get-image-{userId}" }, method = RequestMethod.GET, produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable int userId, ModelMap model) {
		byte[] img = userProfileService.getImageById(userId);
		return img;
	}
	
	 
	   @RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.GET)
	    public String addDocuments(@PathVariable int userId, ModelMap model) {
	        User user = userService.findById(userId);
	        model.addAttribute("user", user);
	 
	        FileBucket fileModel = new FileBucket();
	        model.addAttribute("fileBucket", fileModel);
	 
	        List<UserProfile> documents = userProfileService.findAllByUserId(userId);
	        model.addAttribute("documents", documents);
	         
	        return "dashboard";
	    }
	 
	 
	 private void saveDocument(FileBucket fileBucket, User user) throws IOException{
         
		 UserProfile document = new UserProfile();
	         
	        MultipartFile multipartFile = fileBucket.getFile();
	         
	        document.setName(multipartFile.getOriginalFilename());
	        document.setDescription(fileBucket.getDescription());
	        document.setType(multipartFile.getContentType());
	        document.setContent(multipartFile.getBytes());
	        document.setUser(user);
	        userProfileService.saveDocument(document);
	    }

}
