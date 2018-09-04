package com.irealmar.repository;

import java.util.Hashtable;
import java.util.Map;

/**
 * Clients are store.
 */
// @Component
// @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
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
     * Check the account number existence in the persistence store.
     * @param accountNumber
     *        the account number
     * @return tue if there is an account number is found in the persistence store.
     */
    public boolean accountExists(Long accountNumber) {
        return clients.containsKey(accountNumber);
    }

    /**
     * Gets the corresponding client given an account number.
     * @param accountNumber
     *        the account number
     * @return the client corresponding to the account number
     */
    public Client getClient(Long accountNumber) {
        if (!accountExists(accountNumber)) {
            return null;
        } else {
            return clients.get(accountNumber);
        }

    }

}
