package com.irealmar.repository;

/**
 * A client is identified by the account number which is associated with a pin number and an overdraft.
 */
public class Client {
    private Long accountNumber;
    private int pin;
    private double initialBalance;
    private double overdraft;

    /**
     * Constructor.
     * @param accountNumber
     *        the account number
     * @param pin
     *        the pin number
     * @param initialBalance
     *        the initial balance
     * @param overdraft
     *        the overdraft
     */
    public Client(Long accountNumber, int pin, double initialBalance, double overdraft) {
        super();
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.initialBalance = initialBalance;
        this.overdraft = overdraft;
    }

    /**
     * Getter.
     * @return the pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * Getter.
     * @return the initialBalance
     */
    public double getInitialBalance() {
        return initialBalance;
    }

    /**
     * Getter.
     * @return the overdraft
     */
    public double getOverdraft() {
        return overdraft;
    }

    /**
     * Getter.
     * @return the account number
     */
    public Long getAccountNumber() {
        return this.accountNumber;
    }

}
