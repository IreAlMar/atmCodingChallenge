package com.irealmar.service;

/**
 * TODO: documentar.
 */
public interface IWithdrawalService {
    /**
     * TODO: documentar.
     * @param pin
     *        the pin number
     * @param accountNumber
     *        the account number corresponding to the pin number
     * @return maximum withdrawal amount
     * @throws InvalidPinException
     * @throws InvalidAccountException
     */
    Double getMaximumWithdrawal(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException;

}
