package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.entities.Seller;

import java.util.Optional;


public interface SellerService {

    /**
     * Find and returns a Seller by Id.
     * @param Id
     * @return
     */
    public Optional<Seller> findById(long Id);

    /**
     * Adds a new seller.
     * @param seller
     * @return
     */
    public Seller add(Seller seller);


}
