package com.irealmar.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import com.irealmar.repository.Client;
import com.irealmar.repository.ClientContainer;
import com.irealmar.repository.TransactionContainer;
import com.irealmar.service.impl.BalanceService;
import com.irealmar.service.impl.InvalidAccountException;
import com.irealmar.service.impl.InvalidPinException;

/**
 * Test class for BalanceService.
 */
@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceTest {

    @Mock
    private ClientContainer clientContainer;

    @Mock
    private TransactionContainer transactionContainer;

    @Mock
    private ApplicationContext context;

    @InjectMocks
    private BalanceService balanceService;

    /**
     * Prepare Mocks.
     */
    @Before
    public void prepareMocks() {
        Mockito.when(context.getBean("transactionContainer")).thenReturn(transactionContainer);
        Mockito.when(context.getBean("clientContainer")).thenReturn(clientContainer);
    }

    /**
     * TODO: documentar.
     */
    @Test
    public void itShouldReturnBalanceCheck() {
        Double balance = null;
        Long accountNumber = Long.valueOf(123456789);
        int pin = 1234;
        Client client = new Client(accountNumber, pin, 800.0, 200.0);

        Mockito.when(clientContainer.accountExists(Mockito.anyLong())).thenReturn(true);
        Mockito.when(clientContainer.getClient(Mockito.anyLong())).thenReturn(client);
        Mockito.when(transactionContainer.getLastResultBalance(Mockito.anyLong())).thenReturn(1000.0);

        try {
            balance = balanceService.checkBalance(pin, Long.valueOf(accountNumber));
        } catch (InvalidPinException | InvalidAccountException ex) {
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
    public void itShouldNotExposeTheCustomerBalanceIfTheAccountIsNotFound() throws InvalidPinException,
        InvalidAccountException {
        Long accountNumber = Long.valueOf(11111111);
        Mockito.when(clientContainer.accountExists(Mockito.anyLong())).thenReturn(false);
        balanceService.checkBalance(1234, accountNumber);

    }

    /**
     * The ATM should not expose the customer balance if the pin is incorrect.
     * @throws InvalidAccountException
     *         Exception
     * @throws InvalidPinException
     *         Exception
     */
    @Test(expected = InvalidPinException.class)
    public void itShouldNotExposeTheCustomerBalanceIfThePinIsIncorrect() throws InvalidPinException,
        InvalidAccountException {
        Long accountNumber = Long.valueOf(123456789);
        int pin = 1234;
        Client client = new Client(accountNumber, pin, 800.0, 200.0);

        Mockito.when(clientContainer.accountExists(Mockito.anyLong())).thenReturn(true);
        Mockito.when(clientContainer.getClient(Mockito.anyLong())).thenReturn(client);
        balanceService.checkBalance(1111, Long.valueOf(123456789));
    }

}
