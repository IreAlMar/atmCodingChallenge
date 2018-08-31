package com.irealmar.dbaccess;

import java.util.Hashtable;
import java.util.Map;

/**
 * TODO: documentar.
 */
// TODO: final needs power mockito to test https://www.baeldung.com/mockito-final
public class ClientDDBB {
    private static ClientDDBB clientDDBB = new ClientDDBB();
    // TODO final
    private Map<Long, Client> clients = new Hashtable<Long, Client>();

    private ClientDDBB() {
        clients.put(Long.valueOf(123456789), new Client(Long.valueOf(123456789), 1234, 800.0, 200.0));
        clients.put(Long.valueOf(987654321), new Client(Long.valueOf(987654321), 4321, 1230, 150));
    }

    /**
     * TODO: documentar.
     * @return the client DDBB instance
     */
    public static ClientDDBB getClientDDBBInstance() {
        if (clientDDBB == null) {
            clientDDBB = new ClientDDBB();
        }
        return clientDDBB;
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
