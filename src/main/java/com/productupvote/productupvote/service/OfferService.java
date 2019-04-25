package com.productupvote.productupvote.service;

import com.productupvote.productupvote.domain.Offer;
import com.productupvote.productupvote.domain.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private UserService userService;

    /**
     * This method saves new offer to database.
     *
     * @param offer new offer object.
     */
    public void save(Offer offer) {
        offerRepository.save(offer);
    }
}
