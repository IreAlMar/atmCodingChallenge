package com.irealmar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.irealmar.repository.Client;
import com.irealmar.repository.ClientContainer;
import com.irealmar.repository.TransactionContainer;
import com.irealmar.service.IBalanceService;

/**
 * Service for balance operations.
 */
@Service
public class BalanceService implements IBalanceService {

    @Autowired
    private ApplicationContext context;
    private TransactionContainer transactionContainer;
    private ClientContainer clientContainer;

    @Override
    public Double checkBalance(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException {
        transactionContainer = (TransactionContainer)context.getBean("transactionContainer");
        clientContainer = (ClientContainer)context.getBean("clientContainer");
        // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        // clientContainer = context.getBean(ClientContainer.class);
        // transactionContainer = context.getBean(TransactionContainer.class);
        // context.close();

        if (!clientContainer.accountExists(accountNumber)) {
            throw new InvalidAccountException();

        } else {
            Client client = clientContainer.getClient(accountNumber);
            if (client != null && client.getPin() != pin) {
                throw new InvalidPinException();

            } else {
                return transactionContainer.getLastResultBalance(accountNumber);
            }
        }
    }

}
