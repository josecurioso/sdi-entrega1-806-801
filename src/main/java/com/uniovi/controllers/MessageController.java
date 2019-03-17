package com.uniovi.controllers;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.User;
import com.uniovi.services.MessageService;
import com.uniovi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class MessageController {

	@Autowired
	UsersService usersService; 
	
	@Autowired
	MessageService msgService;
	
	
	/**
	 * Saca los mensajes de una conversación a partir de su id
	 * @param id
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping("/user/offer/message/{id}")
	public String goToChat(@PathVariable Long id,Model model,Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("conversationId", id);
		List<Message> messages=msgService.findMessagesByConversationById(id);
		model.addAttribute("messagesList", messages);
		
		return "/message/chat";
	}

	/**
	 * Cuando se pincha en enviar mensaje crea o recupera la conversación
	 * @param id
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping("/user/offer/conversation/add/{id}")
	public String createConversation(@PathVariable Long id,Model model,Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		model.addAttribute("user", user);
		
		Conversation conv = msgService.getCoversation(user, id);
		model.addAttribute("conversationId", conv.getId());
		List<Message> messages=msgService.findMessagesByConversationById(conv.getId());
		model.addAttribute("messagesList", messages);
		
		return "/message/chat";
	}

	/**
	 * Borra la conversacion a partir de su id
	 * @param id
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping("/user/offer/message/delete/{id}")
	public String delete(@PathVariable Long id,Model model,Principal principal) {
		msgService.deleteConversation(id);
		
		return "redirect:/user/conversations";
	}
	
	/**
	 * Crea una conversacion o recupera si ya hay una y añade el primer mensaje de un usuario a una oferta
	 * @param offerId
	 * @param model
	 * @param message
	 * @param principal
	 * @return la url del mensaje con la Id de la conversacion
	 */
	@RequestMapping(value = "/user/offer/message/add/{converId}", method = RequestMethod.POST) // Adds the msg
	public String sendMessage(@PathVariable Long converId ,Model model,  Message message, Principal principal) {
		String email = principal.getName(); // Es el email
		User autor = usersService.getUserByEmail(email);
		
		Conversation conv = msgService.findConversationById(converId);
		
		msgService.addMsg(message, autor,conv);

		
		model.addAttribute("user", autor);
		String url="redirect:/user/offer/message/"+conv.getId();
		return url;
	}

	@RequestMapping("/user/conversations")
	public String goToConversationList(Model model,Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		model.addAttribute("user", user);
		
		List<Conversation> conversations=msgService.getConversationByUser(user);
		model.addAttribute("conversationList", conversations);
		
		return "/message/conversations";
	}

}
