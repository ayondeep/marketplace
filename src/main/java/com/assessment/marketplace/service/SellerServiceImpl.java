package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class SellerServiceImpl implements SellerService {


    @Resource
    JpaRepository<Seller, Long> jpaRepository;

    @Override
    public Optional<Seller> findById(long Id) {
        return jpaRepository.findById(Id);
    }

    @Override
    public Seller add(Seller seller) {
        return jpaRepository.save(seller);
    }
}
