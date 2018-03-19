package com.assessment.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Project data transfer object.")
public class ProjectDTO {

    @ApiModelProperty(value = "Unique identifer for the project.")
    private long id;

    @ApiModelProperty(required = true, value = "Name of the project.")
    @NotNull(message = "Name is a required field.")
    private String name;

    @ApiModelProperty(value="Description of the project.")
    private String description;

    @ApiModelProperty(required = true, value = "Maximum budget of the project.")
    @NotNull (message = "MaxBudget is a required field.")
    private Double maxBudget;

    @ApiModelProperty(required = true, value="Last date/time to receive a bid for the project.")
    @NotNull (message = "Deadline is a required field.")
    private Date deadline;

    @ApiModelProperty(required = true, value = "Unique identifier of the seller.")
    @NotNull (message = "SellerId is a required field.")
    private long sellerId;

    @ApiModelProperty(value = "Unique identifier for the lowest bid received for the project.")
    private Long lowestBidId;

    @ApiModelProperty(value = "Lowest bid amount received for the project.")
    private Double lowestBidAmount;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(Double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getLowestBidId() {
        return lowestBidId;
    }

    public void setLowestBidId(Long lowestBidId) {
        this.lowestBidId = lowestBidId;
    }

    public Double getLowestBidAmount() {
        return lowestBidAmount;
    }

    public void setLowestBidAmount(Double lowestBidAmount) {
        this.lowestBidAmount = lowestBidAmount;
    }
}
