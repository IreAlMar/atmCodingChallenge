package com.irealmar.service;

import com.irealmar.service.impl.InvalidAccountException;
import com.irealmar.service.impl.InvalidPinException;

/**
 * Interface for the balance service.
 */
public interface IBalanceService {

    /**
     * Returns the balance given an account number in case that the pin and the account number are valid.
     * @param pin
     *        the pin number
     * @param accountNumber
     *        the account number corresponding to the pin number
     * @return the balance
     * @throws InvalidPinException
     *         exception thrown in case of invalid pin
     * @throws InvalidAccountException
     *         exception thrown in case of invalid account
     */
    Double checkBalance(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException;

}
