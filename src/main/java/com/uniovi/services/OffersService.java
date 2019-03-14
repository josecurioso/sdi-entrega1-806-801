package com.uniovi.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class OffersService {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private OffersRepository offersRepository;

	@Autowired
	private UsersRepository usersRepository;

	public Page<Offer> getOffers(Pageable pageable) {
		Page<Offer> offers = offersRepository.findAll(pageable);
		return offers;
	}

	public Offer getOffer(Long id) {
		Set<Offer> consultedList = (Set<Offer>) httpSession.getAttribute("consultedList");
		if (consultedList == null) {
			consultedList = new HashSet<Offer>();
		}
		Offer offerObtained = offersRepository.findById(id).get();
		consultedList.add(offerObtained);
		httpSession.setAttribute("consultedList", consultedList);
		return offerObtained;
	}

	public Page<Offer> getOffersForUser(Pageable pageable, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		offers = offersRepository.findAllByUser(pageable, user);
		return offers;
	}

	public Page<Offer> getBuysForUser(Pageable pageable, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		offers = offersRepository.findAllBuysByUser(pageable, user);
		return offers;
	}

	public Page<Offer> searchOffersByDescriptionAndNameForUser(Pageable pageable, String searchText, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		searchText = "%" + searchText + "%";
		offers = offersRepository.searchByDescriptionNameAndUser(pageable, searchText, user);
		return offers;
	}

	public Page<Offer> searchOffersByDescriptionAndName(Pageable pageable, String searchText) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		searchText = "%" + searchText + "%";
		offers = offersRepository.searchByDescriptionAndName(pageable, searchText);
		return offers;
	}

	public Page<Offer> searchBuysByDescriptionAndNameForUser(Pageable pageable, String searchText, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		searchText = "%" + searchText + "%";
		offers = offersRepository.searchBuysByDescriptionNameAndUser(pageable, searchText, user);
		return offers;
	}

	public void addOffer(Offer offer, User user) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		offer.setUser(user);
		user.getOffers().add(offer);
		offersRepository.save(offer);
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}

	public void buyOffer(Offer offer, User user) {
		offer.setSold(true);
		offer.setBuyer(user);
		offersRepository.save(offer);

		user.getBuys().add(offer);
		System.out.println(user.getMoney());
		System.out.println(offer.getPrice());
		user.setMoney(user.getMoney() - offer.getPrice());
		System.out.println(user.getMoney());
		user  =usersRepository.save(user);

		offer.getBuyer().setMoney(offer.getBuyer().getMoney() + offer.getPrice());
		usersRepository.save(offer.getBuyer());

		System.out.println(user.getMoney());
	}

}
