package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Bid;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface BidService {

    /**
     * Find and returns a Bid by Id.
     * @param Id
     * @return
     */
    public Optional<Bid> findById(long Id);

    /**
     * Saves a Bid.
     * @param bid
     * @return
     */
    public Bid save(Bid bid);


}
