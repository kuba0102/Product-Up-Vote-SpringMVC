package com.productupvote.productupvote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 * Permission
 * This is a User class that will be used to store and return User information.
 * It has variables and each variable has a getter and setter.
 * Variables: Integer id, User user, UserType userType,  boolean userEdit, boolean userView,
 * boolean userDelete, boolean userAdd,  boolean productApprove, boolean productEdit, boolean productView, 
 * productAdd, productDelete, boolean orderEdit, boolean orderView, boolean orderDelete, boolean orderAdd, 
 * boolean customerEdit, boolean customerView, boolean customerDelete and boolean customerAdd.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perm_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NotNull
    private UserType userType;

    // User Variables
    @NotNull
    @Column(name = "user_edit")
    private boolean userEdit;
    @NotNull
    @Column(name = "user_view")
    private boolean userView;
    @NotNull
    @Column(name = "user_delete")
    private boolean userDelete;
    @NotNull
    @Column(name = "user_add")
    private boolean userAdd;

    //Product Variables
    @NotNull
    @Column(name = "product_approve")
    private boolean productApprove;
    @NotNull
    @Column(name = "product_edit")
    private boolean productEdit;
    @NotNull
    @Column(name = "product_view")
    private boolean productView;
    @NotNull
    @Column(name = "product_add")
    private boolean productAdd;
    @NotNull
    @Column(name = "product_delete")
    private boolean productDelete;

    // Order Variables
    @NotNull
    @Column(name = "order_edit")
    private boolean orderEdit;
    @NotNull
    @Column(name = "order_view")
    private boolean orderView;
    @NotNull
    @Column(name = "order_delete")
    private boolean orderDelete;
    @NotNull
    @Column(name = "order_add")
    private boolean orderAdd;


    // Customer Variables
    @NotNull
    @Column(name = "customer_edit")
    private boolean customerEdit;
    @NotNull
    @Column(name = "customer_view")
    private boolean customerView;
    @NotNull
    @Column(name = "customer_delete")
    private boolean customerDelete;
    @NotNull
    @Column(name = "customer_add")
    private boolean customerAdd;

    // Constructor
    public Permission(User user, UserType userType, boolean admin) {
        this.user = user;
        this.userType = userType;
        this.userEdit = admin;
        this.userView = admin;
        this.userDelete = admin;
        this.userAdd = admin;
        this.productApprove = admin;
        this.productEdit = admin;
        this.productView = admin;
        this.productAdd = admin;
        this.productDelete = admin;
        this.orderEdit = admin;
        this.orderView = admin;
        this.orderDelete = admin;
        this.orderAdd = admin;
        this.customerEdit = admin;
        this.customerView = admin;
        this.customerDelete = admin;
        this.customerAdd = admin;
    }

    // No args Constructor
    public Permission(){

    }

    // Getters and Setters
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isUserEdit() {
        return userEdit;
    }

    public void setUserEdit(boolean userEdit) {
        this.userEdit = userEdit;
    }

    public boolean isUserView() {
        return userView;
    }

    public void setUserView(boolean userView) {
        this.userView = userView;
    }

    public boolean isUserDelete() {
        return userDelete;
    }

    public void setUserDelete(boolean userDelete) {
        this.userDelete = userDelete;
    }

    public boolean isUserAdd() {
        return userAdd;
    }

    public void setUserAdd(boolean userAdd) {
        this.userAdd = userAdd;
    }

    public boolean isProductApprove() {
        return productApprove;
    }

    public void setProductApprove(boolean productApprove) {
        this.productApprove = productApprove;
    }

    public boolean isProductEdit() {
        return productEdit;
    }

    public void setProductEdit(boolean productEdit) {
        this.productEdit = productEdit;
    }

    public boolean isProductView() {
        return productView;
    }

    public void setProductView(boolean productView) {
        this.productView = productView;
    }

    public boolean isProductAdd() {
        return productAdd;
    }

    public void setProductAdd(boolean productAdd) {
        this.productAdd = productAdd;
    }

    public boolean isProductDelete() {
        return productDelete;
    }

    public void setProductDelete(boolean productDelete) {
        this.productDelete = productDelete;
    }

    public boolean isOrderEdit() {
        return orderEdit;
    }

    public void setOrderEdit(boolean orderEdit) {
        this.orderEdit = orderEdit;
    }

    public boolean isOrderView() {
        return orderView;
    }

    public void setOrderView(boolean orderView) {
        this.orderView = orderView;
    }

    public boolean isOrderDelete() {
        return orderDelete;
    }

    public void setOrderDelete(boolean orderDelete) {
        this.orderDelete = orderDelete;
    }

    public boolean isOrderAdd() {
        return orderAdd;
    }

    public void setOrderAdd(boolean orderAdd) {
        this.orderAdd = orderAdd;
    }

    public boolean isCustomerEdit() {
        return customerEdit;
    }

    public void setCustomerEdit(boolean customerEdit) {
        this.customerEdit = customerEdit;
    }

    public boolean isCustomerView() {
        return customerView;
    }

    public void setCustomerView(boolean customerView) {
        this.customerView = customerView;
    }

    public boolean isCustomerDelete() {
        return customerDelete;
    }

    public void setCustomerDelete(boolean customerDelete) {
        this.customerDelete = customerDelete;
    }

    public boolean isCustomerAdd() {
        return customerAdd;
    }

    public void setCustomerAdd(boolean customerAdd) {
        this.customerAdd = customerAdd;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", user=" + user +
                ", userType=" + userType +
                ", userEdit=" + userEdit +
                ", userView=" + userView +
                ", userDelete=" + userDelete +
                ", userAdd=" + userAdd +
                ", productApprove=" + productApprove +
                ", productEdit=" + productEdit +
                ", productView=" + productView +
                ", productAdd=" + productAdd +
                ", productDelete=" + productDelete +
                ", orderEdit=" + orderEdit +
                ", orderView=" + orderView +
                ", orderDelete=" + orderDelete +
                ", orderAdd=" + orderAdd +
                ", customerEdit=" + customerEdit +
                ", customerView=" + customerView +
                ", customerDelete=" + customerDelete +
                ", customerAdd=" + customerAdd +
                '}';
    }
}
