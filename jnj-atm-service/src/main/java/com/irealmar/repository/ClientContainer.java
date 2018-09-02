package com.irealmar.repository;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * TODO: documentar.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ClientContainer {

    // TODO final ¿?
    private static Map<Long, Client> clients;

    /**
     * The ATM should also initialize with the following accounts:
     * Account Number PIN Opening Balance Overdraft
     *   123456789   1234       800         200
     *   987654321   4321       1230        150
     */
    public ClientContainer() {
        clients = new Hashtable<Long, Client>();
        clients.put(Long.valueOf(123456789), new Client(Long.valueOf(123456789), 1234, 800.0, 200.0));
        clients.put(Long.valueOf(987654321), new Client(Long.valueOf(987654321), 4321, 1230, 150));
    }

    /**
     * TODO: documentar.
     * @param accountNumber
     *        the account number
     * @return if the account number exists
     */
    public boolean accountExists(Long accountNumber) {
        return clients.containsKey(accountNumber);
    }

    /**
     * TODO: documentar.
     * @param accountNumber
     *        the account number
     * @return the client data
     */
    public Client getClient(Long accountNumber) {
        if (!accountExists(accountNumber)) {
            return null;
        } else {
            return clients.get(accountNumber);
        }

    }

}
