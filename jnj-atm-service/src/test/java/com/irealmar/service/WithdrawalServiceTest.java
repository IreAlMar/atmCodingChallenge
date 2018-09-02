package com.irealmar.service;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

import com.irealmar.repository.CashContainer;
import com.irealmar.repository.Client;
import com.irealmar.repository.ClientContainer;
import com.irealmar.service.impl.WithdrawalService;

/**
 * TODO: documentar.
 */
@RunWith(MockitoJUnitRunner.class)
public class WithdrawalServiceTest {

    @Mock
    private ClientContainer clientContainer;

    @Mock
    private CashContainer cashContainer;

    @Mock
    private IBalanceService balanceService;

    @InjectMocks
    private WithdrawalService withdrawalService;

    /**
     * Prepare tests.
     */

    /**
     * TODO: documentar.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     */
    @Test
    public final void getMaximumWithdrawalSuceedTest() throws InvalidPinException, InvalidAccountException {
        Long accountNumber = Long.valueOf(123456789);
        int pin = 1234;
        Client client = new Client(accountNumber, pin, 800.0, 200.0);
        Double actualBalance = 500.0;
        Double expectedMaximumWithdrawal = client.getOverdraft() + actualBalance;
        TreeMap<Integer, Integer> expectedWithdrawalNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        expectedWithdrawalNotes.put(50, 20);

        Mockito.when(balanceService.checkBalance(pin, accountNumber)).thenReturn(actualBalance);

        Double maximumWithdrawal = withdrawalService.getMaximumWithdrawal(pin, Long.valueOf(accountNumber));
        assertEquals(expectedMaximumWithdrawal, maximumWithdrawal, 0.0);
    }

    /**
     * TODO: documentar.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     */
    // @Test(expected = InvalidAccountException.class)
    // public void getMaxWithdrawalInvalidAccountTest() throws InvalidPinException, InvalidAccountException {

    // Mockito.when(clientContainer.accountExists(accountNumber)).thenReturn(accountExists);
    // Mockito.when(clientContainer.getClient(accountNumber)).thenReturn(client);
    // Mockito.when(cashContainer.calculateWithdrawal(2000)).thenReturn(expectedWithdrawalNotes);
    //
    // }
}
