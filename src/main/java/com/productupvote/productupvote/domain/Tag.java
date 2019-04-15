package com.productupvote.productupvote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * User
 * This is a User class that will be used to store and return User information.
 * It has variables and each variable has a getter and setter.
 * Variables: Integer id, String username, String name, String surname, String email, String password,
 * String salt, Date dateCreated, Date dateUpdated, Date dateOnline and boolean backend.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer id;
    @NotEmpty
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    //Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
