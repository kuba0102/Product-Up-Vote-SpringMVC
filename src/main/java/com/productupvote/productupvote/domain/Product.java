package com.productupvote.productupvote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Product
 * This is a Product class that will be used to store and return Product information.
 * It has variables and each variable has a getter and setter.
 * Variables: Integer id, User user, String name, String description, String image, String approved,
 * Boolean userApproved, int upVotes, Date dateSubmitted and Date dateApproved.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String image;
    @NotEmpty
    private String approved;
    @NotNull
    private Boolean userApproved;
    private int upVotes;
    private Date dateSubmitted;
    private Date dateApproved;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "product")
    @JsonIgnore
    private List<Offer> offers;

    public Product() {
        this.approved = "no";
        this.userApproved = false;
        this.upVotes = 0;
        this.dateSubmitted = new Date();
        this.dateApproved = null;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Boolean getUserApproved() {
        return userApproved;
    }

    public void setUserApproved(Boolean userApproved) {
        this.userApproved = userApproved;
    }
}
