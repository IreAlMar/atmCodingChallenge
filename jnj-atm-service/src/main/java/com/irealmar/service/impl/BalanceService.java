package com.irealmar.service.impl;

import com.irealmar.service.IBalanceService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
public class BalanceService implements IBalanceService {

    @Override
    public Double checkBalance(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException {
        // TODO Implement
        // check pin
        // if pin incorrect throw exception
        // else continue with your life
        // retrieve movements
        // calculate actual balance
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
