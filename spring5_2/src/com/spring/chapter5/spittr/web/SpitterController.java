package com.spring.chapter5.spittr.web;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.chapter5.spittr.Spitter;
import com.spring.chapter5.spittr.Spittle;
import com.spring.chapter5.spittr.data.SpitterRepository;
import com.spring.chapter5.spittr.data.SpittleRepository;

@Controller
@RequestMapping(value = "/spitter")
public class SpitterController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	private SpitterRepository spitterRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}
	
	// register route.
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String showRegisterForm() {
		return "registerForm";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String processRegistration(
			@Valid Spitter spitter, Errors errors) {
		if(errors.hasErrors()) {
			return "registerForm";
		}
			
		spitterRepository.save(spitter);
		return "redirect:/spitter/" + spitter.getUsername();
	}
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public String showSpitterProfile(
			@PathVariable String username,Model model) {
		model.addAttribute("spitter", spitterRepository.findByUsername(username));
		return "profile";
	}
}
 