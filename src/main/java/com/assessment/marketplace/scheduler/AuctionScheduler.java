package com.assessment.marketplace.scheduler;

import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Buyer;
import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.service.BidService;
import com.assessment.marketplace.service.ProjectService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This scheduler checks project's deadline and if it has reached closes the auction for the project. It also notifies
 * the buyer with the winning (lowest) bid.
 */
@Component
public class AuctionScheduler {

    @Resource
    ProjectService projectService;

    @Resource
    BidService bidService;

    @Scheduled(fixedRate = 10000)
    public void notifyWinner() {
        System.out.println("Auction in progress...");
        closeProjectsAndNotifyWinner(findOpenProjectsThatMetDeadline());
    }

    /**
     * Finds open projects that has met project deadline.
     * @return
     */
    protected List<Project> findOpenProjectsThatMetDeadline() {
        Date currentDate = new Date();
        List<Project> projects = projectService.findAll()
                .stream()
                .filter(project -> (project.isOpenForAuction()==true
                        && (project.getDeadline().before(currentDate))))
                .collect(Collectors.toList());


        return projects;
    }

    /**
     * Closes a project for auction and notifies winning bidder.
     * @param projects
     */
    protected void closeProjectsAndNotifyWinner(List<Project> projects) {
        projects.forEach(project -> {
            //close project
            project.setOpenForAuction(false);
            projectService.save(project);
            System.out.println(String.format("Close auction for project %s. It is past deadline.", project.getName()));
            // Notify lowest bidder
            Optional<Bid> lowestBid = projectService.findLowestBid(project);
            if (lowestBid.isPresent()) {
                Buyer buyer = lowestBid.get().getBuyer();
                System.out.println(String.format("Notify buyer %s for winning bid for project %s", buyer.getName(), project.getName()));
            }
        });
    }

}
