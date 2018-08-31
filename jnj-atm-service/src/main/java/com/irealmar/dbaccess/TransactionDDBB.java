package com.irealmar.dbaccess;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * TODO: documentar.
 */
public class TransactionDDBB {
    private static TransactionDDBB transactionDDBB = new TransactionDDBB();
    private Map<Long, SortedSet<Transaction>> transactions;

    private TransactionDDBB() {
        transactions = new Hashtable<Long, SortedSet<Transaction>>();
        SortedSet<Transaction> clientTransactions1 = new TreeSet<Transaction>();
        // Initial balance August 28, 2018 11:38:41 AM
        clientTransactions1.add(new Transaction(new Date(1535456321), 800.0));
        // transaction of +200.0 August 31, 2018 11:37:02 AM
        clientTransactions1.add(new Transaction(new Date(1535715422), 1000.0));
        transactions.put(Long.valueOf(123456789), clientTransactions1);

        SortedSet<Transaction> clientTransactions2 = new TreeSet<Transaction>();
        // Initial balance Thursday, July 5, 2018 11:38:41 AM
        clientTransactions2.add(new Transaction(new Date(1530790721), 1230.0));
        // transaction of -530.0 August 5, 2018 11:38:41 AM
        clientTransactions2.add(new Transaction(new Date(1533469121), 700.0));
        transactions.put(Long.valueOf(987654321), clientTransactions2);
    }

    /**
     * TODO: documentar.
     * @return the transaction DDBB instance
     */
    public static TransactionDDBB getTransactionDDBBInstance() {
        if (transactionDDBB == null) {
            transactionDDBB = new TransactionDDBB();
        }
        return transactionDDBB;
    }

    /**
     * TODO: documentar.
     * @param accountNumber
     *        the account number
     * @return the balance after the last transaction
     */
    public Double getLastResultBalance(Long accountNumber) {
        SortedSet<Transaction> clientTransactions = getClientTransactions(accountNumber);

        return clientTransactions.last().getResultBalance();
    }

    // TODO: throw invalid account in the upper layer
    private SortedSet<Transaction> getClientTransactions(Long accountNumber) {
        return transactions.get(accountNumber);
    }

    // TODO: add transaction
}
