package com.irealmar.service.impl;

import com.irealmar.dbaccess.Client;
import com.irealmar.dbaccess.ClientDDBB;
import com.irealmar.dbaccess.TransactionDDBB;
import com.irealmar.service.IBalanceService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
public class BalanceService implements IBalanceService {
    // @Autowired TODO: any way to use autowired? Why do the test explode when using autowired? Because ClietnDDBB is
    // not initialized, but why?
    private ClientDDBB clientDDBB = ClientDDBB.getClientDDBBInstance();
    private TransactionDDBB transactionDDBB = TransactionDDBB.getTransactionDDBBInstance();

    // @Autowired
    // private ClientDDBB clientDDBB;

    @Override
    public Double checkBalance(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException {
        if (!clientDDBB.accountExists(accountNumber)) {
            throw new InvalidAccountException();

        } else {
            Client client = clientDDBB.getClient(accountNumber);
            if (client.getPin() != pin) {
                throw new InvalidPinException();

            } else {
                return transactionDDBB.getLastResultBalance(accountNumber);
            }
        }
    }

}
