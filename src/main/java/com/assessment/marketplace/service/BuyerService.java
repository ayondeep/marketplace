package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Buyer;
import com.assessment.marketplace.entities.Seller;

import java.util.Optional;


public interface BuyerService {

    /**
     * Find and returns a Buyer by Id.
     * @param Id
     * @return
     */
    public Optional<Buyer> findById(long Id);

    /**
     * Adds a new seller.
     * @param buyer
     * @return
     */
    public Buyer add(Buyer buyer);


}
