package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import com.uniovi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Offer;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

@Controller
public class HomeController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OffersService offersService;
	
	@RequestMapping("/")
	public String index(Model model, Principal principal) {
		User user;
		try{
			String email = principal.getName(); // Es el email
			user = usersService.getUserByEmail(email);
			model.addAttribute("user", user);
			return "redirect:/home";
		}
		catch (Exception e){
			user = new User("", "", "");
		}
		model.addAttribute("user", user);
		return "index";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model, Pageable pageable, Principal principal) {
		System.out.println("requested home");
		User user;
		try{
			String email = principal.getName(); // Es el email
			user = usersService.getUserByEmail(email);
		}
		catch (Exception e){
			System.out.println("error raro");
			user = new User("", "", "");
		}
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersService.getOffers(pageable);
		model.addAttribute("offersList", offers.getContent());
		model.addAttribute("page", offers);
		model.addAttribute("user", user);
		return "home";
	}
}