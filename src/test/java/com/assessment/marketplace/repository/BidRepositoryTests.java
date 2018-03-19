package com.assessment.marketplace.repository;


import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Buyer;
import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.entities.Seller;
import com.assessment.marketplace.repository.BidRepository;
import com.assessment.marketplace.service.BidService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BidRepositoryTests {

    @Resource
    private TestEntityManager entityManager;

    @Resource
    private BidRepository bidRepository;

    @Test
    public void testFindById_shouldReturnABid() {
        // given
        Seller seller = new Seller();
        seller.setName("seller");
        entityManager.persistAndFlush(seller);
        Project project = new Project();
        project.setName("project");
        project.setSeller(seller);
        project.setMaxBudget(1000.00);
        project.setDeadline(new Date());
        project = entityManager.persistAndFlush(project);
        Buyer buyer = new Buyer();
        buyer.setName("buyer");
        entityManager.persistAndFlush(buyer);
        Bid bid = new Bid();
        bid.setAmount(800.00);
        bid.setBuyer(buyer);
        bid.setProject(project);
        bid = entityManager.persistAndFlush(bid);

        // when
        Optional<Bid> found = bidRepository.findById(bid.getId());

        // then
        Assert.assertEquals("Success", bid.getId(), found.get().getId());
    }

    @Test
    public void testFindByProject_ShouldReturnBids() {
        // given
        Seller seller = new Seller();
        seller.setName("seller");
        entityManager.persistAndFlush(seller);
        Project project = new Project();
        project.setName("project");
        project.setSeller(seller);
        project.setMaxBudget(1000.00);
        project.setDeadline(new Date());
        project = entityManager.persistAndFlush(project);
        Buyer buyer = new Buyer();
        buyer.setName("buyer");
        entityManager.persistAndFlush(buyer);
        Bid bid = new Bid();
        bid.setAmount(800.00);
        bid.setBuyer(buyer);
        bid.setProject(project);
        bid = entityManager.persistAndFlush(bid);

        // when
        List<Bid> found = bidRepository.findByProject(project);

        // then
        Assert.assertEquals("Success", bid.getId(), found.get(0).getId());
    }


}
