package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class BuyerServiceImpl implements BuyerService {


    @Resource
    JpaRepository<Buyer, Long> jpaRepository;

    @Override
    public Optional<Buyer> findById(long Id) {
        return jpaRepository.findById(Id);
    }

    @Override
    public Buyer add(Buyer buyer) {
        return jpaRepository.save(buyer);
    }
}
