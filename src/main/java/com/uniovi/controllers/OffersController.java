package com.uniovi.controllers;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddOfferValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class OffersController {

    @Autowired
    private OffersService offersService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AddOfferValidator addOfferValidator;

    private static Logger logger = LoggerFactory.getLogger(OffersController.class);

    @RequestMapping("/offer/list") // List all the offers in the system
    public String getList(Model model, Pageable pageable, Principal principal,
                          @RequestParam(value = "", required = false) String searchText) {
        String email = principal.getName(); // Es el email
        User user = usersService.getUserByEmail(email);
        Page<Offer> offers;
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
        Page<Offer> offers;
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
        Page<Offer> offers;
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
    public String updateList(Model model, Pageable pageable) {
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
            return "offer/add";
        }
        model.addAttribute("user", user);
        logger.info("User " + user.getName() + " created Offer " + offer.getName());
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
        User user = usersService.getUserByEmail(email);
        Offer offer = offersService.getOffer(id);
        if (offer.getUser().getEmail().equals(email)) {
            offersService.deleteOffer(id);
            logger.info("User " + user.getName() + " deleted Offer " + offer.getName());
        }
        else {
            logger.warn("User " + user.getName() + " tried to delete Offer " + offer.getName() + " but it is not his");
        }
        return "redirect:/user/offer/list";
    }

    @RequestMapping("/offer/buy/{id}") // Buys an offer
    public String buyOffer(RedirectAttributes redir, @PathVariable Long id, Principal principal) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        Offer offer = offersService.getOffer(id);
        redir.addAttribute("user", user);

        switch(offersService.buyOffer(offer, user)){
            case 1:
                logger.warn("User " + user.getName() + " tried to buy Offer " + offer.getName() + " but he doesn't have enough money");
                redir.addFlashAttribute("errormsg", "error.buy.nomoney");
                break;
            case 2:
                logger.warn("User " + user.getName() + " tried to buy Offer " + offer.getName() + " but it is not available");
                redir.addFlashAttribute("errormsg", "error.buy.noavailable");
                break;
            case 3:
                logger.warn("User " + user.getName() + " tried to buy Offer " + offer.getName() + " but it is his own");
                redir.addFlashAttribute("errormsg", "error.buy.yours");
                break;
            case 0:
                logger.info("User " + user.getName() + " bought Offer " + offer.getName());
                break;
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

    @RequestMapping("/offer/highlight/{id}") // Highlights an offer
    public String highlightOffer(RedirectAttributes redir, @PathVariable Long id, Principal principal) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        Offer offer = offersService.getOffer(id);
        redir.addAttribute("user", user);

        switch(offersService.highlightOffer(offer, user)){
            case 1:
                logger.warn("User " + user.getName() + " tried to highlight Offer " + offer.getName() + " but he doesn't have enough money");
                redir.addFlashAttribute("errormsg", "error.highlight.nomoney");
                break;
            case 2:
                logger.warn("User " + user.getName() + " tried to highlight Offer " + offer.getName() + " but it is already highlighted");
                redir.addFlashAttribute("errormsg", "error.highlight.alreadyhighlighted");
                break;
            case 3:
                logger.warn("User " + user.getName() + " tried to highlight Offer " + offer.getName() + " but it is not his own");
                redir.addFlashAttribute("errormsg", "error.highlight.notyours");
                break;
            case 0:
                logger.info("User " + user.getName() + " highlighted Offer " + offer.getName());
                break;
        }
        return "redirect:/user/offer/list";
    }
}
