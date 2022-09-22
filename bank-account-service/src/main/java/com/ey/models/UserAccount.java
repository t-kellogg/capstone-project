package com.ey.models;

import javax.persistence.*;
import java.util.List;


public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ua_id")
    private int id;

    private String username;
    private String token;
    private String email;

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "l_name")
    private String lastName;

    @Column(name = "p_no")
    private String phoneNumber;

    private Role role;

    @Column(name = "active")
    private boolean active;

//    @ElementCollection
//    @CollectionTable(name = "user_accounts_bank_accounts",
//            joinColumns = @JoinColumn(name = "user_account_id"))
//    @Column(name="bank_account_id")
//    private List<Integer> bankAccounts;

    public UserAccount() {
    }

    public UserAccount(int id, String username, String token,
                       String email, String firstName, String lastName,
                       String phoneNumber, Role role) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

//    public boolean addBankAccount(Integer id) {
//        if(!bankAccounts.contains(id)) {
//            return bankAccounts.add(id);
//        }
//
//        return false;
//    }

//    public boolean removeBankAccount(Integer id) {
//        if(bankAccounts.contains(id)) {
//            return bankAccounts.remove(id);
//        }
//
//        return false;
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

//    public List<Integer> getBankAccounts() {
//        return bankAccounts;
//    }
//
//    public void setBankAccounts(List<Integer> bankaccounts) {
//        this.bankAccounts = bankaccounts;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
