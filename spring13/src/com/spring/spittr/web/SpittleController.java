package com.spring.spittr.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.spittr.Spittle;
import com.spring.spittr.repository.SpittleRepository;

@Controller
@RequestMapping(value = "/spittle")
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	private SpittleRepository spittleRepository;
	private int limit = 5;
	private int offset = 0;
	private int pagenum = 0;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(
			@PathVariable("spittleId") long spittleId, 
			Model model) {
		Spittle spittle = spittleRepository.findSpittle((int)spittleId);
		if (spittle == null) {
			throw new SpittleNotFoundException();
		}
		model.addAttribute(spittle);
		return "spittle";
	}

	// spittle 列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String spittles(Model model, HttpSession session) {
		if (pagenum == 0) {
			int sum = spittleRepository.getItemSum();
			pagenum = sum % limit == 0 ? sum / limit : sum / limit + 1;
			session.setAttribute("spittle_pagenum", pagenum);
		}
		model.addAttribute("spittles", spittleRepository.findSpittles(limit, offset));
		return "spittles";
	}

	// spittle paging.
	@RequestMapping(value = "/paging", method = RequestMethod.GET)
	public String spittles(@RequestParam(value = "curpage", defaultValue = "1") int curpage, Model model) {
		offset = (curpage - 1) * limit;
		model.addAttribute("mySpittles", spittleRepository.findSpittles(limit, offset));
		model.addAttribute("spittle_nextpage", curpage);
		return "paging_spittles";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String spittle(@RequestParam("spittle_id") int id, Model model) {
		model.addAttribute(spittleRepository.findSpittle(id));
		return "spittle";
	}

	@RequestMapping(value = "/show/{spittle_id}", method = RequestMethod.GET)
	public String spittlePath(@PathVariable("spittle_id") int id, Model model) {
		model.addAttribute(spittleRepository.findSpittle(id));
		return "spittle";
	}
}
