package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Buyer;
import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.entities.Seller;
import com.assessment.marketplace.repository.BidRepository;
import com.assessment.marketplace.repository.ProjectRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ProjectServiceImplTest {

    @TestConfiguration
    static class ProjectServiceImplTestContextConfiguration {

        @Bean
        public ProjectService ProjectService() {
            return new ProjectServiceImpl();
        }
    }

    @Resource
    private ProjectService projectService;

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

        Bid bid1 = new Bid();
        bid1.setId(0);
        bid1.setProject(project);
        bid1.setBuyer(buyer);
        bid1.setAmount(100.00);

        Bid bid2 = new Bid();
        bid2.setId(1);
        bid2.setProject(project);
        bid2.setBuyer(buyer);
        bid2.setAmount(200.00);

        Bid bid3 = new Bid();
        bid3.setId(3);
        bid3.setProject(project);
        bid3.setBuyer(buyer);
        bid3.setAmount(300.00);

        List<Bid> bids = new ArrayList<>();
        bids.add(bid1);
        bids.add(bid2);
        bids.add(bid3);


        Mockito.when(projectRepository.findById(project.getId()))
                .thenReturn(Optional.of(project));

        Mockito.when(bidRepository.findByProject(project)).thenReturn(bids);
        Mockito.when(bidRepository.findById(bid1.getId())).thenReturn(Optional.of(bid1));
        Mockito.when(bidRepository.findById(bid2.getId())).thenReturn(Optional.of(bid2));
        Mockito.when(bidRepository.findById(bid3.getId())).thenReturn(Optional.of(bid3));

    }

    @Test
    public void testProject_findById_shouldFindProject() {
        long id = 0;
        Optional<Project> found = projectService.findById(id);

        Assert.assertEquals(found.get().getId(), id);
    }

    @Test
    public void testProject_findById_shouldNotFindProject() {
        long id = 1;
        Optional<Project> found = projectService.findById(id);

        Assert.assertEquals(found.isPresent(), false);
    }

    @Test
    public void testProject_findById_projectShouldContainLowestBid() {
        long id = 0;
        Optional<Project> found = projectService.findById(id);
        Optional<Bid> lowestBid = bidRepository.findById(projectService.findLowestBid(found.get()).get().getId());

        Assert.assertEquals(lowestBid.get().getAmount(), 100.00, 0);
    }

    @Test
    public void testProject_saveProject_shouldSuccessfullySaveroject() {
        Project Project = new Project();

        Mockito.when(projectRepository.save(Project))
                .thenReturn(Project);

        Project found = projectService.save(Project);

        Assert.assertNotNull(found);
    }
}
