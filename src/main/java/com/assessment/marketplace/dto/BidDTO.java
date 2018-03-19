package com.assessment.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Bid data transfer object.")
public class BidDTO {

    @ApiModelProperty(value = "Unique identifier for the bid.")
    private long id;

    @ApiModelProperty(required = true, value = "Unique identifier of the project for which this bid is placed.")
    @NotNull(message = "ProjectId is a required field.")
    private long projectId;

    @ApiModelProperty(required = true, value = "Unique identifier of the buyer placing this bid.")
    @NotNull (message = "BuyerId is a required field.")
    private long buyerId;

    @ApiModelProperty(required = true, value = "Bid amount.")
    @NotNull (message = "Amount is a required field.")
    private Double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
