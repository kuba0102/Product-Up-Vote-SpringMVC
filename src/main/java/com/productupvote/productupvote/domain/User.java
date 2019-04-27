package com.productupvote.productupvote.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * User
 * This is a User class that will be used to store and return User information.
 * It has variables and each variable has a getter and setter.
 * Variables: Integer id, String username, String name, String surname, String email, String password,
 * String salt, Date dateCreated, Date dateUpdated, Date dateOnline and boolean backend.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    private String salt;
    private Date dateCreated;
    private Date dateUpdated;
    private Date dateOnline;
    private boolean backend;
    private int votes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_product",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
    private List<Product> upVotedProducts;

    public User() {
        this.votes = 10;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateOnline() {
        return dateOnline;
    }

    public void setDateOnline(Date dateOnline) {
        this.dateOnline = dateOnline;
    }

    public boolean isBackend() {
        return backend;
    }

    public void setBackend(boolean backend) {
        this.backend = backend;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public List<Product> getUpVotedProducts() {
        return upVotedProducts;
    }

    public void setUpVotedProducts(List<Product> upVotedProducts) {
        this.upVotedProducts = upVotedProducts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", dateOnline=" + dateOnline +
                '}';
    }

    public List<Product> getUpVotedByFilters(String search, String filter, String descAsc) {
        List<Product> products = new ArrayList<>();
        for (Product p : upVotedProducts) {

            if (!search.equals("")) {
                if (p.getName().toLowerCase().contains(search.toLowerCase())) {
                    if (!products.contains(p)) products.add(p);
                }
            } else {
                products = upVotedProducts;
            }
        }
        if (filter != null) {
            if (filter.equals("name")) products.sort(Comparator.comparing(Product::getName));
            if (filter.equals("top")) products.sort(Comparator.comparing(Product::getUpVotes));
            if (descAsc.equals("desc")) Collections.reverse(products);
        }

        return products;
    }
}
