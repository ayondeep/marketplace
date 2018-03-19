package com.assessment.marketplace.api;

import com.assessment.marketplace.dto.ProjectDTO;
import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.entities.Project;
import com.assessment.marketplace.entities.Seller;
import com.assessment.marketplace.service.ProjectService;
import com.assessment.marketplace.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * REST controller class that provides operations for adding and getting a Project.
 */
@RestController
@RequestMapping(value="/project")
@Api(value = "/project", description = "REST operations for project.")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @Resource
    private SellerService sellerService;

    @RequestMapping (method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "Find a project by Id. Project will include the lowest bid amount received for the project.")
    public ProjectDTO get(@PathVariable long id) {
        return constructDTO(projectService.findById(id).get());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Add a new project")
    public ProjectDTO add(@RequestBody ProjectDTO input) {
        return constructDTO(projectService.save(constructEntity(input)));
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Converts an entity to DTO.
     * @param entity Entity object to be converted to DTO
     * @return DTO
     */
    private ProjectDTO constructDTO(Project entity) {
        ProjectDTO dto = new ProjectDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setSellerId(entity.getSeller().getId());
        Optional<Bid> bid = projectService.findLowestBid(entity);
        if (bid.isPresent()) {
            dto.setLowestBidId(bid.get().getId());
            dto.setLowestBidAmount(bid.get().getAmount());
        }

        return dto;
    }

    /**
     * Converst DTO to Entity.
     * @param dto DTO object to be converted to entity.
     * @return Entity object.
     */
    private Project constructEntity(ProjectDTO dto) {
        Project entity = new Project();
        BeanUtils.copyProperties(dto, entity, "id");

        Optional<Seller> seller = sellerService.findById(dto.getSellerId());
        if (seller.isPresent()) {
            entity.setSeller(seller.get());
        } else {
            throw new IllegalArgumentException(String.format("Cannot locate seller for this project for seller ID %s",
                    dto.getSellerId()));
        }


        return entity;
    }

}
