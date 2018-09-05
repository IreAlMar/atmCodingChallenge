package com.irealmar.response;

/**
 * Response for balance check operations.
 */
public class BalanceResponse {
    private double balance;

    // TOTHINK: I think it is better to sen just result codes in order to make localization easier, but the briefing
    // said to send meaningful messages.
    private String resultCode;

    /**
     * Default constructor.
     */
    public BalanceResponse() {

    }

    /**
     * Constructor.
     * @param balance
     *        the account balance
     * @param resultCode
     *        code result for the operation requested
     */
    public BalanceResponse(double balance, String resultCode) {
        super();
        this.balance = balance;
        this.resultCode = resultCode;
    }

    /**
     * Setter.
     * @param balance
     *        the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Getter.
     * @return the balance
     */
    public double getBalance() {
        return balance;
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
