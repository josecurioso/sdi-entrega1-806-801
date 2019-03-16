package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Message;

public interface  MessageRepository extends CrudRepository<Message, Long>{

	
	@Query("SELECT m FROM Conversation c ,Message m WHERE m.conversation=c AND c.offer.id=?1 AND c.author.id=?2 ")
	List<Message> findMessagesByOfferAndUser(Long idOffer, long idUser);

	
}
