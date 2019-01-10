/**
 * 
 */
package com.ljproject.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ljproject.model.State;
import com.ljproject.repository.StateRepository;

/**
 * @author Nitesh
 *
 */
@Controller
public class StateController {
	 public static final Logger logger = LoggerFactory.getLogger(StateController.class);
	 
	 @Autowired
	 StateRepository stateRepository;
	
	@RequestMapping(value = { "/state" }, method = RequestMethod.GET)
	public ModelAndView statePage(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("stateModel", new State());
		modelAndView.setViewName("state");
		return modelAndView;
	}
	
	 @RequestMapping(value = { "/addstate" }, method = RequestMethod.POST)
		public String addStatePage(@ModelAttribute("stateModel") State state) {
		 stateRepository.save(state);
			return "state";
		}

}
