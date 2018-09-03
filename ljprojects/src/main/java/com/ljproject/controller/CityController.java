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

import com.ljproject.model.City;
import com.ljproject.repository.CityRepository;

/**
 * @author Nitesh
 *
 */
@Controller
public class CityController {
	 public static final Logger logger = LoggerFactory.getLogger(CityController.class);
	 
	 @Autowired
	 CityRepository cityRepository;
	 
	 @RequestMapping(value = { "/city" }, method = RequestMethod.GET)
		public ModelAndView cityPage(Model model) {
			ModelAndView modelAndView = new ModelAndView();
			model.addAttribute("cityModel", new City());
			modelAndView.setViewName("city");
			return modelAndView;
		}
	 
	 @RequestMapping(value = { "/addcity" }, method = RequestMethod.POST)
		public String addCityPage(@ModelAttribute("cityModel") City city) {
			cityRepository.save(city);
			return "city";
		}

}
