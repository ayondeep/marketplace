package com.assessment.marketplace.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Seller data transfer object.")
public class SellerDTO {

    @ApiModelProperty(value = "Unique identifer for the seller.")
    private long id;

    @ApiModelProperty(required = true, value = "Name of the seller.")
    @NotNull (message = "Name is a required field.")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
