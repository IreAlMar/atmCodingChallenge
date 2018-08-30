package com.irealmar.dbaccess;

import java.util.Hashtable;
import java.util.Map;

/**
 * TODO: documentar.
 */
public final class ClientDDBB {
    private Map<Long, Client> clients = new Hashtable<Long, Client>();
    private static ClientDDBB clientDDBB = new ClientDDBB();

    private ClientDDBB() {
        clients.put(Long.valueOf(123456789), new Client(Long.valueOf(123456789), 1234, 800.0, 200.0));
        clients.put(Long.valueOf(987654321), new Client(Long.valueOf(987654321), 4321, 1230, 150));
    }

    /**
     * TODO: documentar.
     * @return the client DDBB
     */
    public static ClientDDBB getClientDDBB() {
        if (clientDDBB == null) {
            clientDDBB = new ClientDDBB();
        }
        return clientDDBB;
    }

}
