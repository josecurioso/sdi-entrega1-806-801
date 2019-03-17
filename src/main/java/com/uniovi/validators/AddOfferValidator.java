package com.uniovi.validators;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddOfferValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Offer offer = (Offer) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");

        if (offer.getName().length() < 2 || offer.getName().length() > 24) {
            errors.rejectValue("name", "Error.offer.name.length");
        }
        if (offer.getDescription().length() < 2 || offer.getDescription().length() > 50) {
            errors.rejectValue("description", "Error.offer.description.length");
        }
        //reject not number
        if (offer.getPrice() == null) {
            errors.rejectValue("price", "Error.offer.price.none");
        }
        if (offer.getPrice() < 0) {
            errors.rejectValue("price", "Error.offer.price.low");
        }
        if (offer.getHighlighted() && offer.getUser().getMoney()<20) {
            errors.rejectValue("highlighted", "error.highlight.nomoney");
        }
    }
}