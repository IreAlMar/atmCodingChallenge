package com.irealmar.service;

import java.util.TreeMap;

public class WithdrawalResult {

    TreeMap<Integer, Integer> withdrawalNotes;
    Double remainingBalance;

    public WithdrawalResult() {
    }

    public WithdrawalResult(TreeMap<Integer, Integer> withdrawalNotes, Double remainingBalance) {
        super();
        this.withdrawalNotes = withdrawalNotes;
        this.remainingBalance = remainingBalance;
    }

    public TreeMap<Integer, Integer> getWithdrawalNotes() {
        return withdrawalNotes;
    }

    public void setWithdrawalNotes(TreeMap<Integer, Integer> withdrawalNotes) {
        this.withdrawalNotes = withdrawalNotes;
    }

    public Double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

}
