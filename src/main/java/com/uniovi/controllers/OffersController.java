package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import com.uniovi.validators.AddOfferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

@Controller
public class OffersController {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private OffersService offersService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AddOfferValidator addOfferValidator;

	@RequestMapping("/offer/list") // List all the offers in the system
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOffersByDescriptionAndName(pageable, searchText);
		} else {
			offers = offersService.getOffers(pageable);
		}
		model.addAttribute("offersList", offers.getContent());
		model.addAttribute("page", offers);
		model.addAttribute("user", user);

		return "offer/list";
	}

	@RequestMapping("/user/offer/list") // List all the offers for a user
	public String getListForUser(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOffersByDescriptionAndNameForUser(pageable, searchText, user);
		} else {
			offers = offersService.getOffersForUser(pageable, user);
		}
		model.addAttribute("offersList", offers.getContent());
		model.addAttribute("page", offers);
		model.addAttribute("user", user);

		return "offer/listown";
	}

	@RequestMapping("/user/buys/list") // List all the buys for a user
	public String getBuysListForUser(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchBuysByDescriptionAndNameForUser(pageable, searchText, user);
		} else {
			offers = offersService.getBuysForUser(pageable, user);
		}
		model.addAttribute("offersList", offers.getContent());
		model.addAttribute("page", offers);
		model.addAttribute("user", user);

		return "offer/listbuys";
	}

	@RequestMapping("/offer/list/update") // Updates the offer list
	public String updateList(Model model, Pageable pageable, Principal principal) {
		Page<Offer> offers = offersService.getOffers(pageable);
		model.addAttribute("offersList", offers.getContent());
		return "offer/list :: tableOffers";
	}

	@RequestMapping("/user/offer/list/update") // Updates the offer list for a user
	public String updateListForUser(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("offersList", offers.getContent());
		return "offer/listown :: tableOffers";
	}

	@RequestMapping("/user/buys/list/update") // Updates the buys list for a user
	public String updateBuysListForUser(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getBuysForUser(pageable, user);
		model.addAttribute("offersList", offers.getContent());
		return "offer/listbuys :: tableOffers";
	}

	@RequestMapping(value = "/user/offer/add", method = RequestMethod.POST) // Adds the offer
	public String setOfferAdd(Model model, @Validated Offer offer, BindingResult result, Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		offersService.addOffer(offer, user);

		addOfferValidator.validate(offer, result);
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "offer/add";
		}
		return "redirect:/user/offer/list";
	}

	@RequestMapping(value = "/user/offer/add") // Returns the page where you add an offer
	public String getOfferAdd(Model model, Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		model.addAttribute("usersList", usersService.getUsers());
		model.addAttribute("user", user);
		model.addAttribute("offer", new Offer());
		return "offer/add";
	}

	@RequestMapping("/offer/delete/{id}") // Deletes an offer
	public String deleteOffer(@PathVariable Long id, Principal principal) {
		String email = principal.getName(); // Es el email
		Offer offer = offersService.getOffer(id);
		if (offer.getUser().getEmail().equals(email)) {
			offersService.deleteOffer(id);
		}
		return "redirect:/user/offer/list";
	}

	@RequestMapping("/offer/buy/{id}") // Deletes an offer
	public String buyOffer(@PathVariable Long id, Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		Offer offer = offersService.getOffer(id);
		if (user.getMoney() >= offer.getPrice()) {
			if (!offer.getSold()) {
				offersService.buyOffer(offer, user);
			} else {
				System.out.println("The offer is already sold"); // error offer sold
			}
			System.out.println("Sold");
		} else {
			System.out.println("Not enough money");
		}
		return "redirect:/offer/list";
	}

	@RequestMapping("/updatecounter")
	public String updateCounter(Model model, Principal principal) {
		String email = principal.getName(); // Es el email
		User user = usersService.getUserByEmail(email);
		model.addAttribute("user", user);
		return "offer/list :: topNav";
	}
}
