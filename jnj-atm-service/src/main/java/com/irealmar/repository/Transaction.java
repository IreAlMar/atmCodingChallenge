package com.irealmar.repository;

import java.util.Date;


/**
 * Transaction is the result of an operation on a client's account.
 */
public class Transaction implements Comparable<Transaction> {
    private Date date;
    private Double resultBalance;

    /**
     * Constructor.
     * @param date
     *        when the transaction took place
     * @param resultBalance
     *        resulting balance after the transaction is done
     */
    public Transaction(Date date, Double resultBalance) {
        super();
        this.date = date;
        this.resultBalance = resultBalance;
    }

    @Override
    /**
     * Compares two transaction depending on the Date of the transaction.
     * @return a value greater than 0 if this transaction Date is before the Date from the argument transaction; and a
     *         value less than 0 if this transaction Date is after the Date from the argument transaction
     */
    public int compareTo(Transaction transaction) {
        return this.date.compareTo(transaction.getDate());
    }
    /**
     * Getter.
     * @return the date of creation of the transaction
     */
    public Date getDate() {
        return date;
    }
    /**
     * Getter.
     * @return the resulting balance after the operation is performed
     */
    public Double getResultBalance() {
        return resultBalance;
    }

    /**
     * Setter.
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Setter.
     * @param resultBalance the resultBalance to set
     */
    public void setResultBalance(Double resultBalance) {
        this.resultBalance = resultBalance;
    }



}
