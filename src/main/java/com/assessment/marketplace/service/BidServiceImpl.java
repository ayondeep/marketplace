package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.repository.BidRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Component
public class BidServiceImpl implements BidService {


    @Resource
    BidRepository bidRepository;

    @Override
    public Optional<Bid> findById(long Id) {
        return bidRepository.findById(Id);
    }

    @Override
    public Bid save(Bid bid) {

        // Validate bid amount
        Project project = bid.getProject();
        double maxBudget = project.getMaxBudget();
        if (bid.getAmount() > maxBudget) {
            throw new IllegalArgumentException(String.format("Bid amount cannot be higher than the max budget amount %s", maxBudget));
        }

        //Validate bid date and time
        Date currentTime = new Date();
        if (project.getDeadline().before(currentTime)) {
            throw new IllegalArgumentException("Cannot place anymore bid on this project as it is past deadline.");
        }

        return bidRepository.save(bid);
    }
}
