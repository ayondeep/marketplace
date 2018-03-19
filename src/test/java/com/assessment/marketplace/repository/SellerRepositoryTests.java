package com.assessment.marketplace.repository;

import com.assessment.marketplace.entities.Seller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SellerRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JpaRepository sellerRepository;

    @Test
    public void whenFindById_thenReturnSeller() {
        // given
        Seller seller = new Seller();
        seller.setName("seller");
        seller = entityManager.persistAndFlush(seller);

        // when
        Optional<Seller> found = sellerRepository.findById(seller.getId());

        // then
        Assert.assertEquals("Success", seller.getId(), found.get().getId());
    }


}
