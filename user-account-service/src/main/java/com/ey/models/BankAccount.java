package com.ey.models;

import javax.persistence.*;

//@Entity
//@Table(name="bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ba_id")
    private int id;

    //@Column(name = "type", nullable = false)
    private AccountType type;

    @Column(name = "balance", columnDefinition = "NUMERIC(13,2)", nullable = false)
    private float balance;

    @Column(name = "active")
    private boolean active;

    public BankAccount() {
    }

    public BankAccount(int id, AccountType type, float balance, boolean active) {
        this.id = id;
        this.type = type;
        this.balance = balance;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", type=" + type +
                ", balance=" + balance +
                ", active=" + active +
                '}';
    }
}
