package com.assessment.marketplace.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROJECT")
public class Project {

    /**
     * Unique identifier for the project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /**
     * Name of the project.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Detailed description of the project.
     */
    @Column (name="DESCRIPTION")
    private String description;

    /**
     * Maximum project budget amount.
     */
    @Column (name = "MAX_BUDGET")
    private Double maxBudget;

    /**
     * Deadline for accepting bid for the project.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "DEADLINE")
    private Date deadline;


    /**
     * Seller who posted this project on the market place.
     */
    @ManyToOne
    @JoinColumn (name = "SELLER_ID")
    private Seller seller;

    /**
     * Indicates if this project is open for auction and can accept bids.
     * Value of TRUE indicates it is open; value of FALSE indicates otherwise.
     */
    @Column (name = "OPEN_FOR_AUCTION")
    private boolean openForAuction = true;


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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean isOpenForAuction() {
        return openForAuction;
    }

    public void setOpenForAuction(boolean openForAuction) {
        this.openForAuction = openForAuction;
    }
}
