package com.assessment.marketplace.repository;

import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    public List<Bid> findByProject(Project project);
}
