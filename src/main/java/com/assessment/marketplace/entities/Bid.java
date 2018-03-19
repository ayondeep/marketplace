package com.assessment.marketplace.entities;

import javax.persistence.*;

@Entity
@Table(name = "BID")
public class Bid implements Comparable<Bid> {

    /**
     * Unique identifier for the bid.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /**
     * Project that owns this bid.
     */
    @ManyToOne
    @JoinColumn (name = "PROJECT_ID")
    private Project project;

    /**
     * Buyer that placed this bid.
     */
    @ManyToOne
    @JoinColumn (name = "BUYER_ID")
    private Buyer buyer;

    /**
     * Bid amount.
     */
    private Double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Override
    public int compareTo(Bid bid) {
        if (this.amount >  bid.amount) return 1;
        if (this.amount <  bid.amount) return -1;
        return 0;
    }
}
