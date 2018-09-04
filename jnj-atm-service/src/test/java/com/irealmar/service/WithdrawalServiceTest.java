package com.irealmar.service;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.mockito.Mock;

import com.irealmar.exception.InsuficientCashException;
import com.irealmar.exception.InsuficientFundsException;
import com.irealmar.exception.InvalidAccountException;
import com.irealmar.exception.InvalidPinException;
import com.irealmar.exception.UnavailableAmountException;
import com.irealmar.repository.CashContainer;
import com.irealmar.repository.Client;
import com.irealmar.repository.ClientContainer;
import com.irealmar.repository.TransactionContainer;
import com.irealmar.service.impl.WithdrawalResult;
import com.irealmar.service.impl.WithdrawalService;

/**
 * Test class for WithdrawalService.
 */
@RunWith(MockitoJUnitRunner.class)
public class WithdrawalServiceTest {

    @Mock
    private ClientContainer clientContainer;

    @Mock
    private CashContainer cashContainer;

    @Mock
    private TransactionContainer transactionContainer;

    @Mock
    private IBalanceService balanceService;

    @Mock
    private ApplicationContext context;

    @InjectMocks
    private WithdrawalService withdrawalService;

    /**
     * Prepare Mocks.
     */
    @Before
    public void prepareMocks() {
        Mockito.when(context.getBean("transactionContainer")).thenReturn(transactionContainer);
        Mockito.when(context.getBean("clientContainer")).thenReturn(clientContainer);
        Mockito.when(context.getBean("cashContainer")).thenReturn(cashContainer);
    }

    /**
     * The ATM should check the maximum withdrawal amount (if any).
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     */
    @Test
    public final void itShouldCheckMaxWithdrawalIfAny() throws InvalidPinException, InvalidAccountException {

        Long accountNumber = Long.valueOf(123456789);
        int pin = 1234;
        Client client = new Client(accountNumber, pin, 800.0, 200.0);
        Double actualBalance = 500.0;
        Double expectedMaximumWithdrawal = client.getOverdraft() + actualBalance;
        TreeMap<Integer, Integer> expectedWithdrawalNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        expectedWithdrawalNotes.put(50, 14);

        Mockito.when(balanceService.checkBalance(pin, accountNumber)).thenReturn(actualBalance);

        Mockito.when(clientContainer.accountExists(Mockito.anyLong())).thenReturn(true);
        Mockito.when(clientContainer.getClient(Mockito.anyLong())).thenReturn(client);
        Mockito.when(cashContainer.calculateWithdrawal(Mockito.anyInt())).thenReturn(expectedWithdrawalNotes);

        Double maximumWithdrawal = withdrawalService.getMaximumWithdrawal(pin, Long.valueOf(accountNumber));
        assertEquals(expectedMaximumWithdrawal, maximumWithdrawal, 0.0);
    }

    /**
     * The ATM should check a possible withdrawal and if successful return the details of the notes that would be
     * dispensed along with remaining balance. The ATM should dispense the minimum number of notes per withdrawal.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     * @throws UnavailableAmountException
     *         Exception
     * @throws InsuficientCashException
     *         Exception
     * @throws InsuficientFundsException
     *         Exception
     */
    @Test
    public final void itShouldDetailTheNotesAndRemainingBalance() throws InvalidPinException, InvalidAccountException,
        InsuficientFundsException, InsuficientCashException, UnavailableAmountException {

        Long accountNumber = Long.valueOf(123456789);
        int pin = 1234;
        Client client = new Client(accountNumber, pin, 800.0, 200.0);
        int amountWithdrawal = 100;
        Double expectedBalance = 500.0;
        Double expectedBalanceAfterWithdrawal = expectedBalance - amountWithdrawal;
        TreeMap<Integer, Integer> expectedWithdrawalNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        expectedWithdrawalNotes.put(50, 2);
        Integer totalCash = 2000;
        WithdrawalResult expectedWithdrawalResult = new WithdrawalResult(expectedWithdrawalNotes,
            expectedBalanceAfterWithdrawal);

        Mockito.when(balanceService.checkBalance(pin, accountNumber)).thenReturn(expectedBalance).thenReturn(
            expectedBalanceAfterWithdrawal);
        Mockito.when(clientContainer.accountExists(Mockito.anyLong())).thenReturn(true);
        Mockito.when(clientContainer.getClient(Mockito.anyLong())).thenReturn(client);
        Mockito.when(cashContainer.getTotalCash()).thenReturn(totalCash);
        Mockito.when(cashContainer.calculateWithdrawal(Mockito.anyInt())).thenReturn(expectedWithdrawalNotes);
        Mockito.when(cashContainer.calculateWithdrawal(amountWithdrawal)).thenReturn(expectedWithdrawalNotes);

        WithdrawalResult actualWithdrawalResult = withdrawalService.dispenseFunds(pin, Long.valueOf(accountNumber),
            amountWithdrawal);

        assertEquals(expectedWithdrawalResult.getRemainingBalance(), actualWithdrawalResult.getRemainingBalance());
        assertEquals(expectedWithdrawalResult.getWithdrawalNotes(), actualWithdrawalResult.getWithdrawalNotes());
    }

    /**
     * The ATM cannot dispense more money than it holds.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     * @throws UnavailableAmountException
     *         Exception
     * @throws InsuficientCashException
     *         Exception
     * @throws InsuficientFundsException
     *         Exception
     */
    @Test(expected = InsuficientCashException.class)
    public final void itShouldNotdDispenseMoreMoneyThanItHolds() throws InvalidPinException, InvalidAccountException,
        InsuficientFundsException, InsuficientCashException, UnavailableAmountException {

        Long accountNumber = Long.valueOf(123456789);
        int pin = 1234;
        Client client = new Client(accountNumber, pin, 800.0, 200.0);
        int amountWithdrawal = 100;
        Double expectedBalance = 500.0;
        Double expectedBalanceAfterWithdrawal = expectedBalance - amountWithdrawal;
        TreeMap<Integer, Integer> expectedWithdrawalNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        expectedWithdrawalNotes.put(50, 2);
        Integer totalCash = 5;

        Mockito.when(balanceService.checkBalance(pin, accountNumber)).thenReturn(expectedBalance).thenReturn(
            expectedBalanceAfterWithdrawal);
        Mockito.when(clientContainer.accountExists(Mockito.anyLong())).thenReturn(true);
        Mockito.when(clientContainer.getClient(Mockito.anyLong())).thenReturn(client);
        Mockito.when(cashContainer.getTotalCash()).thenReturn(totalCash);

        withdrawalService.dispenseFunds(pin, Long.valueOf(accountNumber), amountWithdrawal);
    }

    /**
     * The customer cannot withdraw more funds then they have access to.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     * @throws UnavailableAmountException
     *         Exception
     * @throws InsuficientCashException
     *         Exception
     * @throws InsuficientFundsException
     *         Exception
     */
    @Test(expected = InsuficientFundsException.class)
    public final void itShouldNotdDispenseMoreMoneyThanTheCustomersFunds() throws InvalidPinException,
        InvalidAccountException,
        InsuficientFundsException, InsuficientCashException, UnavailableAmountException {

        Long accountNumber = Long.valueOf(123456789);
        int pin = 1234;
        Client client = new Client(accountNumber, pin, 800.0, 200.0);
        int amountWithdrawal = 1000;
        Double expectedBalance = 5.0;
        Double expectedBalanceAfterWithdrawal = expectedBalance - amountWithdrawal;
        TreeMap<Integer, Integer> expectedWithdrawalNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        expectedWithdrawalNotes.put(50, 2);

        Mockito.when(balanceService.checkBalance(pin, accountNumber)).thenReturn(expectedBalance).thenReturn(
            expectedBalanceAfterWithdrawal);
        Mockito.when(clientContainer.accountExists(Mockito.anyLong())).thenReturn(true);
        Mockito.when(clientContainer.getClient(Mockito.anyLong())).thenReturn(client);

        withdrawalService.dispenseFunds(pin, Long.valueOf(accountNumber), amountWithdrawal);
    }

    /**
     * The ATM should not dispense funds if the pin is incorrect.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     * @throws UnavailableAmountException
     *         Exception
     * @throws InsuficientCashException
     *         Exception
     * @throws InsuficientFundsException
     *         Exception
     */
    @Test(expected = InvalidPinException.class)
    public final void itShouldNotdDispenseFundsIfThePinIsIncorrect() throws InvalidPinException,
        InvalidAccountException,
        InsuficientFundsException, InsuficientCashException, UnavailableAmountException {

        Long accountNumber = Long.valueOf(123456789);
        int pin = 1234;
        Client client = new Client(accountNumber, pin, 800.0, 200.0);
        int amountWithdrawal = 1000;
        TreeMap<Integer, Integer> expectedWithdrawalNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        expectedWithdrawalNotes.put(50, 2);

        Mockito.when(clientContainer.accountExists(Mockito.anyLong())).thenReturn(true);
        Mockito.when(clientContainer.getClient(Mockito.anyLong())).thenReturn(client);

        withdrawalService.dispenseFunds(-1, Long.valueOf(accountNumber), amountWithdrawal);
    }

    /**
     * The ATM should only dispense the exact amount requested.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     * @throws UnavailableAmountException
     *         Exception
     * @throws InsuficientCashException
     *         Exception
     * @throws InsuficientFundsException
     *         Exception
     */
    @Test(expected = UnavailableAmountException.class)
    public final void itShouldDispenseTheExactAmountRequested() throws InvalidPinException, InvalidAccountException,
        InsuficientFundsException, InsuficientCashException, UnavailableAmountException {

        Long accountNumber = Long.valueOf(123456789);
        int pin = 1234;
        Client client = new Client(accountNumber, pin, 800.0, 200.0);
        int amountWithdrawal = 101;
        Double expectedBalance = 500.0;
        Double expectedBalanceAfterWithdrawal = expectedBalance - amountWithdrawal;
        TreeMap<Integer, Integer> expectedWithdrawalNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        expectedWithdrawalNotes.put(50, 2);
        Integer totalCash = 2000;

        Mockito.when(balanceService.checkBalance(pin, accountNumber)).thenReturn(expectedBalance).thenReturn(
            expectedBalanceAfterWithdrawal);
        Mockito.when(clientContainer.accountExists(Mockito.anyLong())).thenReturn(true);
        Mockito.when(clientContainer.getClient(Mockito.anyLong())).thenReturn(client);
        Mockito.when(cashContainer.getTotalCash()).thenReturn(totalCash);
        Mockito.when(cashContainer.calculateWithdrawal(Mockito.anyInt())).thenReturn(expectedWithdrawalNotes);
        Mockito.when(cashContainer.calculateWithdrawal(amountWithdrawal)).thenReturn(expectedWithdrawalNotes);

        withdrawalService.dispenseFunds(pin, Long.valueOf(accountNumber), amountWithdrawal);

    }
}
