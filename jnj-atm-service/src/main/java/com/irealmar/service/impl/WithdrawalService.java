package com.irealmar.service.impl;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.irealmar.Application;
import com.irealmar.repository.CashContainer;
import com.irealmar.repository.Client;
import com.irealmar.repository.ClientContainer;
import com.irealmar.service.IBalanceService;
import com.irealmar.service.IWithdrawalService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
@Service
public class WithdrawalService implements IWithdrawalService {

    private ClientContainer clientContainer;
    private CashContainer cashContainer;

    @Autowired
    private IBalanceService balanceService;

    @Override
    public Double getMaximumWithdrawal(int pin, Long accountNumber) throws InvalidPinException,
        InvalidAccountException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        cashContainer = context.getBean(CashContainer.class);
        clientContainer = context.getBean(ClientContainer.class);
        context.close();
        Double overdraft = 0.0;
        Double clientBalance = balanceService.checkBalance(pin, accountNumber);

        // TODO: change to avoid duplicated code from BalanceService.java
        if (!clientContainer.accountExists(accountNumber)) {
            throw new InvalidAccountException();

        } else {
            Client client = clientContainer.getClient(accountNumber);
            if (client != null && client.getPin() != pin) {
                throw new InvalidPinException();

            } else if (client != null) {
                overdraft = client.getOverdraft();
            }
        }

        TreeMap<Integer, Integer> withdrawalNotes = cashContainer.calculateWithdrawal(new Double(clientBalance
            + overdraft).intValue());
        return new Double(withdrawalNotes.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum());
    }

}
