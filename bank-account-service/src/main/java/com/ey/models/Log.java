package com.ey.models;

import javax.persistence.*;

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="l_id")
    int id;

    @Column(name="action", nullable = false)
    Action action;

    @Column(name="amount", columnDefinition = "NUMERIC(13,2)", nullable = false)
    private float amount;

    @Column(name="timestamp", nullable = false)
    private long timestamp;

    @Column(name="user_account_id")
    private int userAccountId;

    @JoinColumn(name="from_bank_id")
    @ManyToOne
    private BankAccount fromBank;

    @JoinColumn(name="to_bank_id")
    @ManyToOne
    private BankAccount toBank;

    public Log() {
    }

    public Log(int id, float amount, long timestamp, int userAccountId, BankAccount fromBank, BankAccount toBank) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.userAccountId = userAccountId;
        this.fromBank = fromBank;
        this.toBank = toBank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public BankAccount getFromBank() {
        return fromBank;
    }

    public void setFromBank(BankAccount fromBank) {
        this.fromBank = fromBank;
    }

    public BankAccount getToBank() {
        return toBank;
    }

    public void setToBank(BankAccount toBank) {
        this.toBank = toBank;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", user_account_id=" + userAccountId +
                ", from_bank=" + fromBank +
                ", to_bank=" + toBank +
                '}';
    }
}
