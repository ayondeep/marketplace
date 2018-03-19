package com.assessment.marketplace.api;

import com.assessment.marketplace.dto.BuyerDTO;
import com.assessment.marketplace.entities.Buyer;
import com.assessment.marketplace.service.BuyerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value="/buyer")
@Api(value = "/buyer", description = "REST operations for buyer.")
public class BuyerController {

    @Resource
    private BuyerService buyerService;

    @RequestMapping (method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "Find a seller by Id.")
    public BuyerDTO get (@PathVariable long id) {

        Optional<Buyer> buyer= buyerService.findById(id);

        return  constructDTO(buyer.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Add a new buyer")
    public BuyerDTO add(@Valid @RequestBody BuyerDTO input) {

        return constructDTO(buyerService.add(constructEntity(input)));
    }

    private BuyerDTO constructDTO(Buyer buyer) {
        BuyerDTO dto = new BuyerDTO();
        dto.setName(buyer.getName());
        dto.setId(buyer.getId());

        return dto;
    }

    private Buyer constructEntity(BuyerDTO dto) {
        Buyer entity = new Buyer();
        entity.setName(dto.getName());
        return entity;
    }


    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}