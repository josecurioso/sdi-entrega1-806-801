package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

	@Query("SELECT c FROM Conversation c WHERE c.author=?1 AND c.offer=?2 ")
	Conversation findByUserAndOffer(User author, Offer offer);

	
	@Query("SELECT c FROM Conversation c WHERE c.author=?1 OR c.offer.user=?1  ")
	List<Conversation> findConversationsByUser(User user);

}
