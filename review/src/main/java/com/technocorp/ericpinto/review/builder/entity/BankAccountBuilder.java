package com.technocorp.ericpinto.review.builder.entity;

import java.util.Locale;

public class BankAccountBuilder {

    private long accountNumber;
    private String owner;
    private String branch;
    private Double balance;
    private Double interestRate;

    public static class Builder{

        private long accountNumber;

        private String owner;
        private String branch;
        private Double balance;
        private Double interestRate;

        public Builder(long accountNumber){
            this.accountNumber = accountNumber;
        }

        public Builder withOwner(String owner){
            this.owner = owner;
            return this;
        }

        public Builder atBranch(String branch){
            this.branch = branch;
            return this;
        }

        public Builder openingBalance(Double balance){
            this.balance = balance;
            return this;
        }

        public Builder atRate(Double interestRate){
            this.interestRate = interestRate;
            return this;
        }

        public BankAccountBuilder build() {
            BankAccountBuilder account = new BankAccountBuilder();
            account.accountNumber = this.accountNumber;
            account.owner = this.owner;
            account.branch = this.branch;
            account.balance = this.balance;
            account.interestRate = this.interestRate;

            return account;
        }
    }

    private BankAccountBuilder(){
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "BankAccountBuilder{" +
                "accountNumber=" + accountNumber +
                ", owner='" + owner + '\'' +
                ", branch='" + branch + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                '}';
    }
}