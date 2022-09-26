package com.ey.models;

public class AuthorizationForm {

    private int userAccountId;
    private int bankAccountId;

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public AuthorizationForm(int userAccountId, int bankAccountId) {
        this.userAccountId = userAccountId;
        this.bankAccountId = bankAccountId;
    }

    public AuthorizationForm() {
    }
}