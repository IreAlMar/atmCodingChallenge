package com.irealmar.dbaccess;

import java.util.Date;

/**
 * TODO: documentar.
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
     * @return a value less than 0 if this transaction Date is before the Date from the argument transaction; and a
     *         value greater than 0 if this transaction Date is after the Date from the argument transaction
     */
    public int compareTo(Transaction transaction) {
        return transaction.getDate().compareTo(this.date);
    }

    /**
     * TODO: documentar.
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * TODO: documentar.
     * @return the resultBalance
     */
    public Double getResultBalance() {
        return resultBalance;
    }

}
