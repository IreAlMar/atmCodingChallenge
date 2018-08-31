package com.irealmar.service.impl;

import org.springframework.stereotype.Service;

import com.irealmar.service.IWithdrawalService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
@Service
public class WithdrawalService implements IWithdrawalService {

    @Override
    public Double getMaximumWithdrawal(int pin, Long accountNumber) throws InvalidPinException,
        InvalidAccountException {
        // TODO Implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
