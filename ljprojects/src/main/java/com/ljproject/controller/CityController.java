/**
 * 
 */
package com.ljproject.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ljproject.model.City;

import com.ljproject.repository.CityRepository;
import com.ljproject.repository.UserProfileRepository;
import com.ljproject.service.CityService;

/**
 * @author Nitesh
 *
 */
@Controller
public class CityController {
	 public static final Logger logger = LoggerFactory.getLogger(CityController.class);
	 
	 @Autowired
	 CityRepository cityRepository;
	 
	 @Autowired
	 UserProfileRepository userProfileRepository;
	 
	 @Autowired
	 CityService cityService;
	 
		 
	 @RequestMapping(value = { "/city" }, method = RequestMethod.GET)
		public ModelAndView cityPage(Model model) {
			ModelAndView modelAndView = new ModelAndView();
			model.addAttribute("cityModel", new City());
			List<City> listcity=cityService.listCity();
			model.addAttribute("listcity", listcity);
			modelAndView.setViewName("city");
			
			return modelAndView;
		}
	 
	 @RequestMapping(value = { "/addcity" }, method = RequestMethod.POST)
		public String addCityPage(@ModelAttribute("cityModel") City city,Model model) {
			cityService.saveCity(city);
			List<City> listcity=cityService.listCity();
			model.addAttribute("listcity", listcity);
			return "city";
		}
	 
	 @RequestMapping(value = { "/admin/deletecity/{id}" }, method = RequestMethod.GET)
	    public String deleteUser(@PathVariable long id,Model model) {
	       cityService.deleteCityById(id);
	       System.out.println("thsis is test");
	       List<City> listcity=cityService.listCity();
			model.addAttribute("listcity", listcity);
	        return "redirect:/city";
	    }
	 
	

}
