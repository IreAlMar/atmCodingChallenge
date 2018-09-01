package com.irealmar.service.impl;

import org.springframework.stereotype.Service;

import com.irealmar.dbaccess.Client;
import com.irealmar.dbaccess.ClientDDBB;
import com.irealmar.dbaccess.TransactionDDBB;
import com.irealmar.service.IBalanceService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
@Service
public class BalanceService implements IBalanceService {

    private TransactionDDBB transactionDDBB = TransactionDDBB.getTransactionDDBBInstance();
    private ClientDDBB clientDDBB = ClientDDBB.getClientDDBBInstance();

    @Override
    public Double checkBalance(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException {
        if (!clientDDBB.accountExists(accountNumber)) {
            throw new InvalidAccountException();

        } else {
            Client client = clientDDBB.getClient(accountNumber);
            if (client != null && client.getPin() != pin) {
                throw new InvalidPinException();

            } else {
                return transactionDDBB.getLastResultBalance(accountNumber);
            }
        }
    }

}
