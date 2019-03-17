package com.uniovi.services;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Offer> getHighlightedOffers() {
        List<Offer> offers = offersRepository.getHighlightedOffers();
        return offers;
    }

    public Offer getOffer(Long id) {
        Set<Offer> consultedList = (Set<Offer>) httpSession.getAttribute("consultedList");
        if (consultedList == null) {
            consultedList = new HashSet<>();
        }
        Offer offerObtained = offersRepository.findById(id).get();
        consultedList.add(offerObtained);
        httpSession.setAttribute("consultedList", consultedList);
        return offerObtained;
    }

    public Page<Offer> getOffersForUser(Pageable pageable, User user) {
        Page<Offer> offers = offersRepository.findAllByUser(pageable, user);
        return offers;
    }

    public Page<Offer> getBuysForUser(Pageable pageable, User user) {
        Page<Offer> offers = offersRepository.findAllBuysByUser(pageable, user);
        return offers;
    }

    public Page<Offer> searchOffersByDescriptionAndNameForUser(Pageable pageable, String searchText, User user) {
        searchText = "%" + searchText + "%";
        Page<Offer> offers = offersRepository.searchByDescriptionNameAndUser(pageable, searchText, user);
        return offers;
    }

    public Page<Offer> searchOffersByDescriptionAndName(Pageable pageable, String searchText) {
        searchText = "%" + searchText + "%";
        Page<Offer> offers = offersRepository.searchByDescriptionAndName(pageable, searchText);
        return offers;
    }

    public Page<Offer> searchBuysByDescriptionAndNameForUser(Pageable pageable, String searchText, User user) {
        searchText = "%" + searchText + "%";
        Page<Offer> offers = offersRepository.searchBuysByDescriptionNameAndUser(pageable, searchText, user);
        return offers;
    }

    public void addOffer(Offer offer, User user) {
        offer.setUser(user);
        user.setMoney(user.getMoney()-20);
        usersRepository.save(user);
        offersRepository.save(offer);
    }

    public void deleteOffer(Long id) {
        offersRepository.deleteById(id);
        //int num = offersRepository.deleteManually(id);
    }

    public int buyOffer(Offer offer, User user) {
        if (user.getMoney() < offer.getPrice())
            return 1;
        if (offer.getSold())
            return 2;
        if (offer.getUser().equals(user))
            return 3;

        double price = offer.getPrice();
        double newUserAmount = user.getMoney() - price;
        offer.setBuyer(user);
        user.setMoney(newUserAmount);


        offer.getUser().setMoney(offer.getUser().getMoney() + price);

        offersRepository.save(offer);
        usersRepository.save(user);
        return 0;
    }

    public int highlightOffer(Offer offer, User user) {
        if (user.getMoney() < 20)
            return 1;
        if (offer.getHighlighted())
            return 2;
        if (!offer.getUser().equals(user))
            return 3;

        offer.setHighlighted(true);
        user.setMoney(user.getMoney() - 20);

        offersRepository.save(offer);
        usersRepository.save(user);
        return 0;
    }
}
