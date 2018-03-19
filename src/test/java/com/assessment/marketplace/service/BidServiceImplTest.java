package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Buyer;
import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.entities.Seller;
import com.assessment.marketplace.repository.BidRepository;
import com.assessment.marketplace.repository.ProjectRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BidServiceImplTest {

    @TestConfiguration
    static class BidServiceImplTestContextConfiguration {

        @Bean
        public BidService BidService() {
            return new BidServiceImpl();
        }
    }

    @Resource
    private BidService bidService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private BidRepository bidRepository;


    @Before
    public void setUp() {

        Seller seller = new Seller();
        seller.setName("seller");

        Project project = new Project();
        project.setName("Project");
        project.setId(0);
        project.setSeller(seller);

        Buyer buyer = new Buyer();
        buyer.setName("buyer");

        Bid bid = new Bid();
        bid.setId(0);
        bid.setProject(project);
        bid.setBuyer(buyer);
        bid.setAmount(100.00);


        Mockito.when(bidRepository.findById(bid.getId()))
                .thenReturn(Optional.of(bid));


    }

    @Test
    public void testBid_findById_shouldFindBid() {
        long id = 0;
        Optional<Bid> found = bidService.findById(id);

        Assert.assertEquals(found.get().getId(), id);
    }

    @Test
    public void testBid_findById_shouldNotFindProject() {
        long id = 1;
        Optional<Bid> found = bidService.findById(id);

        Assert.assertEquals(found.isPresent(), false);
    }

    @Test
    public void testBid_saveBid_shouldSuccessfullySaveBid() {
        Project project = new Project();
        project.setMaxBudget(1000.00);
        project.setDeadline(new Date());

        Bid bid = new Bid();
        bid.setProject(project);
        bid.setAmount(100.00);

        Mockito.when(bidRepository.save(bid))
                .thenReturn(bid);

        Bid found = bidService.save(bid);

        Assert.assertNotNull(found);
    }


    @Test (expected = IllegalArgumentException.class)
    public void testBid_saveBid_shouldThrowException_BidAmountGreaterThanMaxBudget() {
        Project project = new Project();
        project.setMaxBudget(1000.00);
        project.setDeadline(new Date());

        Bid bid = new Bid();
        bid.setProject(project);
        bid.setAmount(1001.00);

        Mockito.when(bidRepository.save(bid))
                .thenReturn(bid);
        bidService.save(bid);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBid_saveBid_shouldThrowException_PastDeadline()  {
        Project project = new Project();
        project.setMaxBudget(1000.00);
        Date today = new Date();
        project.setDeadline(new Date(today.getTime() - (1000 * 60 * 60 * 24)));

        Bid bid = new Bid();
        bid.setProject(project);
        bid.setAmount(100.00);

        Mockito.when(bidRepository.save(bid))
                .thenReturn(bid);
        bidService.save(bid);
    }


}
