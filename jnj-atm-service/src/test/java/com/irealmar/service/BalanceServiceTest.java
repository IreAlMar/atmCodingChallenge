package com.irealmar.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.irealmar.service.impl.BalanceService;
import com.irealmar.service.impl.InvalidAccountException;
import com.irealmar.service.impl.InvalidPinException;

/**
 * Test class for BalanceService.
 */
@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceTest {

    @InjectMocks
    private BalanceService balanceService;

    /**
     * TODO: documentar.
     */
    @Test
    public void getBalanceSucceedTest() {
        Double balance = null;
        try {
            balance = balanceService.checkBalance(1234, Long.valueOf(123456789));
        } catch (InvalidPinException | InvalidAccountException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        if (balance == null) {
            throw new NullPointerException();
        }
        assertEquals(balance, 1000.0, 0.0);

    }

    /**
     * TODO: documentar.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     */
    @Test(expected = InvalidAccountException.class)
    public void getBalanceInvalidAccountTest() throws InvalidPinException, InvalidAccountException {
        balanceService.checkBalance(1234, Long.valueOf(11111111));

    }

    /**
     * TODO: documentar.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     */
    @Test(expected = InvalidPinException.class)
    public void getBalanceInvalidPinTest() throws InvalidPinException, InvalidAccountException {
        balanceService.checkBalance(1111, Long.valueOf(123456789));
    }

}
