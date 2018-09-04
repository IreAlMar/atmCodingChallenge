package com.irealmar.service;

import com.irealmar.exception.InsuficientCashException;
import com.irealmar.exception.InsuficientFundsException;
import com.irealmar.exception.InvalidAccountException;
import com.irealmar.exception.InvalidPinException;
import com.irealmar.exception.UnavailableAmountException;
import com.irealmar.service.impl.WithdrawalResult;

/**
 * Service for withdrawal operations.
 */
public interface IWithdrawalService {
    /**
     * Calculate maximum withdrawal possible.
     * @param pin
     * @param accountNumber
     * @return maximum withdrawal amount possible
     * @throws InvalidPinException
     *         exception thrown in case of invalid pin
     * @throws InvalidAccountException
     *         exception thrown in case of invalid account
     */
    Double getMaximumWithdrawal(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException;

    /**
     * Perform a withdrawal.
     * @param pin
     * @param accountNumber
     * @param amount
     * @return the result of the withdrawal operation
     * @throws InvalidPinException
     *         exception thrown in case of invalid pin
     * @throws InvalidAccountException
     *         exception thrown in case of invalid account
     * @throws InsuficientFundsException
     * @throws InsuficientCashException
     * @throws UnavailableAmountException
     */
    WithdrawalResult dispenseFunds(int pin, Long accountNumber, int amount) throws InvalidPinException,
        InvalidAccountException, InsuficientFundsException, InsuficientCashException, UnavailableAmountException;

}
