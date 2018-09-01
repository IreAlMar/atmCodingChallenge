package com.irealmar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irealmar.dbaccess.Client;
import com.irealmar.dbaccess.ClientDDBB;
import com.irealmar.service.IBalanceService;
import com.irealmar.service.IWithdrawalService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
@Service
public class WithdrawalService implements IWithdrawalService {

    private ClientDDBB clientDDBB = ClientDDBB.getClientDDBBInstance();
    @Autowired
    private IBalanceService balanceService;

    @Override
    public Double getMaximumWithdrawal(int pin, Long accountNumber) throws InvalidPinException,
        InvalidAccountException {

        Double maxWithdrawal = 0.0;
        Double overdraft = 0.0;
        Double clientBalance = balanceService.checkBalance(pin, accountNumber);

        // TODO: change to avoid duplicated code from BalanceService.java
        if (!clientDDBB.accountExists(accountNumber)) {
            throw new InvalidAccountException();

        } else {
            Client client = clientDDBB.getClient(accountNumber);
            if (client != null && client.getPin() != pin) {
                throw new InvalidPinException();

            } else if (client != null) {
                overdraft = client.getOverdraft();
            }
        }

        maxWithdrawal = clientBalance + overdraft;

        return maxWithdrawal;
    }
    
//            if(result.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum() != withdrawalAmount) {
    // throw new InvalidWithdrawalAmountException
    //
    // }

}
