package com.irealmar.service;

import com.irealmar.service.impl.InsuficientCashException;
import com.irealmar.service.impl.InsuficientFundsException;
import com.irealmar.service.impl.InvalidAccountException;
import com.irealmar.service.impl.InvalidPinException;
import com.irealmar.service.impl.UnavailableAmountException;

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
