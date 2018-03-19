package com.assessment.marketplace.repository;

import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.entities.Seller;
import com.assessment.marketplace.repository.ProjectRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void whenFindById_thenReturnProject() {
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

        // when
        Optional<Project> found = projectRepository.findById(project.getId());

        // then
        Assert.assertEquals("Success", project.getId(), found.get().getId());
    }
}
