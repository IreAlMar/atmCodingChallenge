package com.irealmar.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.irealmar.controller.BalanceController;
import com.irealmar.dbaccess.ClientDDBB;
import com.irealmar.dbaccess.Transactions;

/**
 * Test class for BalanceService.
 */
@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceTest {
    private Transactions transactions;

    private ClientDDBB clientsDDBB;

    @InjectMocks
    private BalanceController balanceController;

    /**
     * TODO: documentar.
     */
    @Test(expected = InvalidPinException.class)
    public void getBalanceInvalidPin() {

    }

}
