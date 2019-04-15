package com.productupvote.productupvote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Product
 * This is a User class that will be used to store and return User information.
 * It has variables and each variable has a getter and setter.
 * Variables: Integer id, String username, String name, String surname, String email, String password,
 * String salt, Date dateCreated, Date dateUpdated, Date dateOnline and boolean backend.
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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NotNull
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "product")
    @JsonIgnore
    private List<Tag> tags;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String image;

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
}