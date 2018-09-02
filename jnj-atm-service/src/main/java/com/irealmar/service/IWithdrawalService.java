package com.irealmar.service;

import java.util.TreeMap;

/**
 * TODO: documentar.
 */
public interface IWithdrawalService {
    /**
     * Request maximum possible withdrawal.
     * @param pin
     *        the pin number
     * @param accountNumber
     *        the account number corresponding to the pin number
     * @return maximum withdrawal amount
     * @throws InvalidPinException
     * @throws InvalidAccountException
     */
    Double getMaximumWithdrawal(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException;

    /**
     * Dispense funds.
     * @param pin
     *        the pin number
     * @param accountNumber
     *        the account number corresponding to the pin number
     * @return maximum withdrawal amount
     * @throws InvalidPinException
     * @throws InvalidAccountException
     */
    TreeMap<Integer, Integer> dispenseFunds(int pin, Long accountNumber) throws InvalidPinException,
        InvalidAccountException;

}
