package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationRepository;
import com.uniovi.repositories.MessageRepository;
import com.uniovi.repositories.OffersRepository;

@Service
public class MessageService {

	@Autowired
	OffersService offerService;
	
	@Autowired
	ConversationRepository conversationRepository;
	
	@Autowired
	OffersRepository offerRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	public void addMsg(Message message, User author, Long offerId) {
		Offer offer=offerRepository.findById(offerId).get();
		Conversation conv=conversationRepository.findByUserAndOffer(author,offer);
		if(conv==null) {
			conv=new Conversation(author,offer);
			conversationRepository.save(conv);
		}
		message.setConversation(conv);
		message.setAuthorMsg(author);
		
		messageRepository.save(message);
		conversationRepository.save(conv);
		System.out.println("Mensaje enviado por:"+"---->"+message.toString());
		System.out.println("Mensaje enviado por:"+conv.getAuthor().getEmail()+"---->"+message.toString());
	}

	public List<Message> getMessagesByIdAndUser(Long idOffer, long idUser) {
		
		return messageRepository.findMessagesByOfferAndUser(idOffer,idUser);
	}

	public List<Conversation> getConversationByUser(User user) {
		return conversationRepository.findConversationsByUser(user);
		
	}

	public void deleteConversation(Long id) {
		conversationRepository.deleteById(id);
		
	}


}
