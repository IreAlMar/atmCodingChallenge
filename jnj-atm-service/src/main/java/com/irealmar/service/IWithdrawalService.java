package com.irealmar.service;

import com.irealmar.service.impl.InsuficientCashException;
import com.irealmar.service.impl.InsuficientFundsException;
import com.irealmar.service.impl.InvalidAccountException;
import com.irealmar.service.impl.InvalidPinException;
import com.irealmar.service.impl.UnavailableAmountException;

public interface IWithdrawalService {
    Double getMaximumWithdrawal(int pin, Long accountNumber) throws InvalidPinException, InvalidAccountException;

    WithdrawalResult dispenseFunds(int pin, Long accountNumber, int amount) throws InvalidPinException,
        InvalidAccountException, InsuficientFundsException, InsuficientCashException, UnavailableAmountException;

}
