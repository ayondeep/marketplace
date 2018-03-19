package com.assessment.marketplace.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Buyer data transfer object.")
public class BuyerDTO {

    @ApiModelProperty(value = "Unique identifer for the buyer.")
    private long id;

    @ApiModelProperty (required = true, value = "Name of the buyer.")
    @NotNull(message = "Name is a required field.")
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
