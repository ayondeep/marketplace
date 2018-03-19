package com.assessment.marketplace.api;

import com.assessment.marketplace.dto.BidDTO;
import com.assessment.marketplace.entities.*;
import com.assessment.marketplace.entities.Bid;
import com.assessment.marketplace.service.BidService;
import com.assessment.marketplace.service.BuyerService;
import com.assessment.marketplace.service.ProjectService;
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
 * REST controller class that provides operations for adding and getting a Bid.
 */
@RestController
@RequestMapping(value="/bid")
@Api(value = "/bid", description = "REST operations for bid.")
public class BidController {

    @Resource
    private BidService bidService;

    @Resource
    private BuyerService buyerService;

    @Resource
    private ProjectService projectService;

    @RequestMapping (method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "Find a bid by Id.")
    public BidDTO get (@PathVariable long id) {
        return constructDTO(bidService.findById(id).get());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Add a new bid.")
    public BidDTO add(@RequestBody BidDTO input) {
        return constructDTO(bidService.save(constructEntity(input)));
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Converts an entity to DTO.
     * @param entity
     * @return
     */
    private BidDTO constructDTO(Bid entity) {
        BidDTO dto = new BidDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setBuyerId(entity.getBuyer().getId());
        dto.setProjectId(entity.getProject().getId());

        return dto;
    }

    /**
     * Converts a DTO to entity.
     * @param dto
     * @return
     */
    private Bid constructEntity(BidDTO dto) {
        Bid entity = new Bid();
        BeanUtils.copyProperties(dto, entity, "id");

        Optional<Buyer> buyer = buyerService.findById(dto.getBuyerId());
        if (buyer.isPresent()) {
            entity.setBuyer(buyer.get());
        } else {
            throw new IllegalArgumentException(String.format("Cannot locate buyer for this bid for buyer ID %s",
                    dto.getBuyerId()));
        }
        Optional<Project> project = projectService.findById(dto.getProjectId());
        if (project.isPresent()) {
            entity.setProject(project.get());
        } else {
            throw new IllegalArgumentException(String.format("Cannot locate project for this bid for project ID %s",
                    dto.getProjectId()));
        }


        return entity;
    }

}