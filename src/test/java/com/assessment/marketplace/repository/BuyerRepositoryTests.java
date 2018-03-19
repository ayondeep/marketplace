package com.assessment.marketplace.repository;

import com.assessment.marketplace.entities.Buyer;
import com.assessment.marketplace.entities.Seller;
import com.assessment.marketplace.repository.BuyerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BuyerRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BuyerRepository buyerRepository;

    @Test
    public void whenFindById_thenReturnBuyer() {
        // given
        Buyer buyer = new Buyer();
        buyer.setName("buyer");
        buyer = entityManager.persistAndFlush(buyer);

        // when
        Optional<Buyer> found = buyerRepository.findById(buyer.getId());

        // then
        Assert.assertEquals("Success", buyer.getId(), found.get().getId());
    }
}
