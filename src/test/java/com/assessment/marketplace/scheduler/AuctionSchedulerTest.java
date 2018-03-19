package com.assessment.marketplace.scheduler;

import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Buyer;
import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.entities.Seller;
import com.assessment.marketplace.service.BidService;
import com.assessment.marketplace.service.ProjectService;
import com.assessment.marketplace.service.ProjectServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
public class AuctionSchedulerTest {

    @TestConfiguration
    static class AuctionSchedulerTestContextConfiguration {

        @Bean
        public AuctionScheduler AuctionScheuler() {
            return new AuctionScheduler();
        }
    }

    @Resource
    private AuctionScheduler auctionScheduler;

    @MockBean
    ProjectService projectService;

    @MockBean
    BidService bidService;


    @Before
    public void setUp() {

        Date today = new Date();
        Seller seller = new Seller();
        seller.setName("seller");

        Buyer buyer = new Buyer();
        buyer.setName("buyer");

        // Project has a deadline of today
        Project project = new Project();
        project.setName("Project");
        project.setId(0);
        project.setSeller(seller);
        project.setDeadline(today);
        project.setOpenForAuction(true);

        Bid bid1 = new Bid();
        bid1.setId(0);
        bid1.setProject(project);
        bid1.setBuyer(buyer);
        bid1.setAmount(100.00);

        // Project has a future deadline
        Project project2 = new Project();
        project2.setName("Project2");
        project2.setId(1);
        project2.setSeller(seller);
        project2.setDeadline(new Date(today.getTime() + (1000 * 60 * 60 * 24)));
        project2.setOpenForAuction(true);

        Bid bid2 = new Bid();
        bid2.setId(1);
        bid2.setProject(project2);
        bid2.setBuyer(buyer);
        bid2.setAmount(100.00);


        List<Project> projects = new ArrayList<>();
        projects.add(project);
        projects.add(project2);

        Mockito.when(projectService.findAll())
                .thenReturn(projects);
        Mockito.when(projectService.findLowestBid(project))
                .thenReturn(Optional.of(bid1));
        Mockito.when(projectService.findLowestBid(project2))
                .thenReturn(Optional.of(bid2));
        Mockito.when(bidService.findById(bid1.getId())).thenReturn(Optional.of(bid1));
        Mockito.when(bidService.findById(bid2.getId())).thenReturn(Optional.of(bid2));
    }


    @Test
    public void test_ShouldCloseProjects() {

        List<Project> openProjects = auctionScheduler.findOpenProjectsThatMetDeadline();
        auctionScheduler.closeProjectsAndNotifyWinner(openProjects);

        List<Project> closeProjects = projectService.findAll().stream()
                .filter(p -> p.isOpenForAuction()==false)
                .collect(Collectors.toList());

        Assert.assertEquals(1, closeProjects.size());
    }
}
