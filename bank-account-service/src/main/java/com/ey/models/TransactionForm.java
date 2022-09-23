package com.ey.models;

public class TransactionForm {

    private String username;
    private String token;
    private int fromBankId;
    private int toBankId;
    private Action action;

    private float transactionAmount;

    public TransactionForm() {
    }

    public TransactionForm(String username, String token, int fromBankId, int toBankId, Action action, float transactionAmount) {
        this.username = username;
        this.token = token;
        this.fromBankId = fromBankId;
        this.toBankId = toBankId;
        this.action = action;
        this.transactionAmount = transactionAmount;
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

    public int getFromBankId() {
        return fromBankId;
    }

    public void setFromBankId(int fromBankId) {
        this.fromBankId = fromBankId;
    }

    public int getToBankId() {
        return toBankId;
    }

    public void setToBankId(int toBankId) {
        this.toBankId = toBankId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "TransactionForm{" +
                "username=" + username +
                ", token='" + token + "'" +
                ", fromBankId=" + fromBankId +
                ", toBankId=" + toBankId +
                ", action=" + action +
                ", transactionAmount=" + transactionAmount +
                '}';
    }
}


