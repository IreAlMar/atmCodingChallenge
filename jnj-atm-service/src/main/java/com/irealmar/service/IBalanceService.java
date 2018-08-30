package com.irealmar.service;

/**
 * TODO: documentar.
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
     */
    Double checkBalance(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException;

}
