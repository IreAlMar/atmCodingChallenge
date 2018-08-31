package com.irealmar.controller;

/**
 * TODO: documentar.
 */
public class MaxWithdrawalResponse {
    private double maxWithdrawal;
    private String resultCode;

    /**
     * Constructor.
     * @param maxWithdrawal
     *        maximum withdrawal amount
     * @param resultCode
     *        code result for the operation requested
     */
    public MaxWithdrawalResponse(double maxWithdrawal, String resultCode) {
        super();
        this.maxWithdrawal = maxWithdrawal;
        this.resultCode = resultCode;
    }

    /**
     * Getter.
     * @return the maxWithdrawal
     */
    public double getMaxWithdrawal() {
        return maxWithdrawal;
    }

    /**
     * Setter.
     * @param maxWithdrawal
     *        the maxWithdrawal to set
     */
    public void setMaxWithdrawal(double maxWithdrawal) {
        this.maxWithdrawal = maxWithdrawal;
    }

    /**
     * Getter.
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Setter.
     * @param resultCode
     *        the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

}
