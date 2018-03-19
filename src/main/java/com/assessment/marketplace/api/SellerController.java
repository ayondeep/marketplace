package com.assessment.marketplace.api;

import com.assessment.marketplace.dto.SellerDTO;
import com.assessment.marketplace.entities.Seller;
import com.assessment.marketplace.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value="/seller")
@Api(value = "/seller", description = "REST operations for seller.")
public class SellerController {

    @Resource
    private SellerService sellerService;

    @RequestMapping (method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "Find a seller by Id.")
    public SellerDTO get (@PathVariable long id) {
        return constructDTO(sellerService.findById(id).get());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Add a new seller")
    public SellerDTO add(@Valid @RequestBody SellerDTO input) {
        return constructDTO(sellerService.add(constructEntity(input)));
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    private SellerDTO constructDTO(Seller seller) {
        SellerDTO dto = new SellerDTO();
        dto.setName(seller.getName());
        dto.setId(seller.getId());

        return dto;
    }

    private Seller constructEntity(SellerDTO dto) {
        Seller entity = new Seller();
        entity.setName(dto.getName());
        return entity;
    }

}