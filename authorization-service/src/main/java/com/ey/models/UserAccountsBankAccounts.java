package com.ey.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="user_accounts_bank_accounts")
public class UserAccountsBankAccounts {
    @Id
    @Column(name="user_account_Id")
    private int userAccountId;
    @Column(name="bank_account_Id")
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
}
