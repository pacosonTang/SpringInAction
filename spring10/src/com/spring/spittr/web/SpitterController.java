package com.spring.spittr.web;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.spittr.Spitter;
import com.spring.spittr.repository.SpitterRepository;

@Controller
@RequestMapping(value = "/spitter")
public class SpitterController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	private SpitterRepository spitterRepository;
	
	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}
	
	// login route.
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam String username, 
			@RequestParam String password,
			HttpSession session) {
		String pass = spitterRepository.findPassByUsername(username);
		
		if(password.equals(pass)) {
			session.setAttribute("cur_user", username);
			return "home";
		} else {
			return "login";
		}
	}
	
	// register route.
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterForm(Model model) {
		model.addAttribute("spitter", new Spitter());
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(
			@RequestPart(name="profilePicture",required=false) MultipartFile profilePicture, 
			@Valid Spitter spitter,
			HttpServletRequest request,
			RedirectAttributes model,
			Errors errors) {
		
		if (errors.hasErrors()) {
			return "registerForm";
		}
		
		String catalina = System.getProperty("catalina.base");
		spitterRepository.save(spitter);
		
		// profilePicture.getContentType() == image/jpeg.
		// set the path of storing is the key.		
		String filetype = profilePicture.getContentType().substring(6);
		String headPath= catalina + File.separator + "webapps" + File.separator + 
				"profileHead" + File.separator +  
				spitter.getUsername() + "." + filetype;
		try {
			if(profilePicture.getSize() > 0) {
				profilePicture.transferTo(new File(headPath));
			}
		} catch (IOException e) {			
			return "registerForm";
		}
		model.addFlashAttribute("headPath", headPath);		
		return "redirect:/spitter/" + spitter.getUsername();
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(
			@PathVariable String username, Model model) {
		if(!model.containsAttribute("spitter")) {
			model.addAttribute("spitter", spitterRepository.findByUsername(username));
		}
		return "profile";
	}
	
	/*@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String processRegistration1(Spitter spitter, Model model) {
		spitterRepository.save(spitter);
		model.addAttribute("username", spitter.getUsername());
		return "redirect:/spitter/{username}";
	}*/

	@RequestMapping(value = "/transmit", method = RequestMethod.GET)
	public String processRegistration2(RedirectAttributes model) {		
		model.addAttribute("username", "jaychou");
		model.addFlashAttribute("spitter", new Spitter("jaychou", "root", "pacoson", "tang", "test@163.com"));
		
		return "redirect:/spitter/{username}";
	}
}
