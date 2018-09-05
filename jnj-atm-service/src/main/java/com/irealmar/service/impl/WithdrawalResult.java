package com.irealmar.service.impl;

import java.util.TreeMap;

/**
 * The withdrawal result.
 */
public class WithdrawalResult {

    TreeMap<Integer, Integer> withdrawalNotes;
    Double remainingBalance;

    /**
     * Default constructor.
     */
    public WithdrawalResult() {
    }

    /**
     * Constructor.
     * @param withdrawalNotes
     *        the withdrawal notes
     * @param remainingBalance
     *        the remaining balance
     */
    public WithdrawalResult(TreeMap<Integer, Integer> withdrawalNotes, Double remainingBalance) {
        super();
        this.withdrawalNotes = withdrawalNotes;
        this.remainingBalance = remainingBalance;
    }

    /**
     * Getter.
     * @return the withdrawal notes
     */
    public TreeMap<Integer, Integer> getWithdrawalNotes() {
        return withdrawalNotes;
    }

    /**
     * Setter.
     * @param withdrawalNotes
     *        the withdrawal notes
     */
    public void setWithdrawalNotes(TreeMap<Integer, Integer> withdrawalNotes) {
        this.withdrawalNotes = withdrawalNotes;
    }

    /**
     * Getter.
     * @return the remaining balance
     */
    public Double getRemainingBalance() {
        return remainingBalance;
    }

    /**
     * Setter.
     * @param remainingBalance
     *        the remaining balance
     */
    public void setRemainingBalance(Double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

}
