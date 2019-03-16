package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Message;
import com.uniovi.entities.User;
import com.uniovi.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	OffersService offerService;
	
	@Autowired
	MessageRepository messageRepository;
	
	public void addMsg(Message message, User author, Long offerId) {
		message.setAuthor(author);
		message.setOffer(offerService.getOffer(offerId));
		messageRepository.save(message);
		System.out.println("Mensaje enviado por:"+message.getAuthor().getEmail()+"---->"+message.toString());
	}

	public List<Message> getMessagesByIdAndUser(Long idOffer, long idUser) {
		
		return messageRepository.findMessagesByOfferAndUser(idOffer,idUser);
	}


}
