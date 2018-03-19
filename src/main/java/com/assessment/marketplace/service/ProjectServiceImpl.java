package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.repository.BidRepository;
import com.assessment.marketplace.repository.ProjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectRepository projectRepository;

    @Resource
    BidRepository bidRepository;

    @Override
    public Optional<Project> findById(long Id) {
        return projectRepository.findById(Id);
    }

    @Override
    public Project save(Project project) {

        return projectRepository.save(project);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    /**
     * Finds the lowest bid for the project.
     * @param project
     * @return
     */
    @Override
    public Optional<Bid> findLowestBid(Project project) {
        Optional<Bid> lowestBid = Optional.empty();
        List<Bid> bids =   bidRepository.findByProject(project);
        if (!CollectionUtils.isEmpty(bids)) {
            Collections.sort(bids);
            lowestBid = Optional.of(bids.get(0));
        }

        return lowestBid;
    }


}
