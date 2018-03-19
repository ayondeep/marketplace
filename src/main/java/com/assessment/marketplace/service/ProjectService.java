package com.assessment.marketplace.service;

import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Project;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    /**
     * Find and returns a Project by Id.
     * @param Id
     * @return
     */
    public Optional<Project> findById(long Id);

    /**
     * Saves a  project.
     * @param project
     * @return
     */
    public Project save (Project project);

    /**
     * Returns all projects.
     * @return
     */
    public List<Project> findAll();

    /**
     * Returns lowest bid for the project.
     * @param project
     * @return
     */
    public Optional<Bid> findLowestBid(Project project);


}
