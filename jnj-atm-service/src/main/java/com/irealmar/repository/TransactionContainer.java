package com.irealmar.repository;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionContainer {

    private Map<Long, SortedSet<Transaction>> transactions;

    public TransactionContainer() {
        transactions = new Hashtable<Long, SortedSet<Transaction>>();
        SortedSet<Transaction> clientTransactions1 = new TreeSet<Transaction>();
        // Initial balance August 28, 2018 11:38:41 AM
        clientTransactions1.add(new Transaction(new Date(1535456321), 800.0));
//        // transaction of +200.0 August 31, 2018 11:37:02 AM
//        clientTransactions1.add(new Transaction(new Date(1535715422), 1000.0));
        transactions.put(Long.valueOf(123456789), clientTransactions1);

        SortedSet<Transaction> clientTransactions2 = new TreeSet<Transaction>();
        // Initial balance Thursday, July 5, 2018 11:38:41 AM
        clientTransactions2.add(new Transaction(new Date(1530790721), 1230.0));
//        // transaction of -530.0 August 5, 2018 11:38:41 AM
//        clientTransactions2.add(new Transaction(new Date(1533469121), 700.0));
        transactions.put(Long.valueOf(987654321), clientTransactions2);
    }

    public Double getLastResultBalance(Long accountNumber) {

        SortedSet<Transaction> clientTransactions = getClientTransactions(accountNumber);

        return clientTransactions.last().getResultBalance();
    }

    private SortedSet<Transaction> getClientTransactions(Long accountNumber) {
        return transactions.get(accountNumber);
    }

    public void addTransaction(Long accountNumber, Double amount) {

        Double resultBalance = new Double(amount) + getLastResultBalance(accountNumber);
        SortedSet<Transaction> updatedClientTransactions = transactions.get(accountNumber);

        updatedClientTransactions.add(new Transaction(new Date(), resultBalance));
        transactions.put(accountNumber, updatedClientTransactions);
    }
}
