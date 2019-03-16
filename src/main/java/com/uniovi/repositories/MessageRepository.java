package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Message;

public interface  MessageRepository extends CrudRepository<Message, Long>{

	
	@Query("SELECT m FROM Message m , Offer o , User u WHERE o.id=?1 AND m.offer=o  AND m.author=u  AND u.id= ?2  ")
	List<Message> findMessagesByOfferAndUser(Long idOffer, long idUser);

	
}
