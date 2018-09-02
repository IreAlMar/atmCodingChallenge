package com.irealmar.service.impl;

import org.springframework.stereotype.Service;

import com.irealmar.repository.Client;
import com.irealmar.repository.ClientContainer;
import com.irealmar.repository.TransactionDDBB;
import com.irealmar.service.IBalanceService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
@Service
public class BalanceService implements IBalanceService {

    private TransactionDDBB transactionDDBB = TransactionDDBB.getTransactionDDBBInstance();
    private ClientContainer clientDDBB = new ClientContainer();

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
