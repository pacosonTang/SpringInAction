package com.spring.chapter5.spittr.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.chapter5.spittr.Spittle;
import com.spring.chapter5.spittr.data.SpittleRepository;

@Controller
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

	@RequestMapping(value = "/spittles", method = RequestMethod.GET)
	public List<Spittle> spittles(Model model, HttpSession session) {
		if(pagenum == 0) {
			int sum = spittleRepository.getItemSum();
			pagenum = sum%limit==0 ? sum/limit : sum/limit+1;
			session.setAttribute("pagenum", pagenum);
		}
		return spittleRepository.findSpittles(limit,offset);
	}// 没有输入参数的case下
	
	// 分页路由.
	@RequestMapping(value="/paging", method = RequestMethod.GET)
	public String spittles(
			@RequestParam(value="curpage", defaultValue="1") int curpage,
			Model model) {
		offset = (curpage-1) * limit;
		model.addAttribute("mySpittles", spittleRepository.findSpittles(limit, offset));
		model.addAttribute("nextpage", curpage);
		return "paging";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String spittle(@RequestParam("spittle_id") int id, Model model) {
		model.addAttribute(spittleRepository.findSpittle(id));
		return "spittle";
	}

	@RequestMapping(value = "/show/{spittle_id}", method = RequestMethod.GET)
	public String spittlePath(
			@PathVariable("spittle_id") int id, Model model) {
		model.addAttribute(spittleRepository.findSpittle(id));
		return "spittle";
	}
}
