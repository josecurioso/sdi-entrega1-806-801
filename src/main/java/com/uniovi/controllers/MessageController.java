package com.uniovi.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Message;
import com.uniovi.entities.User;
import com.uniovi.services.MessageService;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

@Controller
public class MessageController {

	@Autowired
	UsersService usersService; 
	
	@Autowired
	MessageService msgService;
	
	
	
	@RequestMapping("/user/offer/message/{id}")
	public String goToChat(@PathVariable Long id,Model model,Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("offerId", id);
		List<Message> messages=msgService.getMessagesByIdAndUser(id,user.getId());
		model.addAttribute("messagesList", messages);
		
		
		
		return "/message/chat";
	}
	@RequestMapping("/message/chat/{id}")
	public String chat(@PathVariable Long id,Model model,Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("offerId", id);
		List<Message> messages=msgService.getMessagesByIdAndUser(id,user.getId());
		model.addAttribute("messagesList", messages);
		
		
		
		return "/message/chat";
	}
	
	@RequestMapping(value = "/user/offer/message/add/{offerId}", method = RequestMethod.POST) // Adds the offer
	public String sendMessage(@PathVariable Long offerId ,Model model,  Message message, BindingResult result, Principal principal) {
		String email = principal.getName(); // Es el email
		User autor = usersService.getUserByEmail(email);
		
		msgService.addMsg(message, autor,offerId);

		
		model.addAttribute("user", autor);
		
		return "message/chat";
	}

}
