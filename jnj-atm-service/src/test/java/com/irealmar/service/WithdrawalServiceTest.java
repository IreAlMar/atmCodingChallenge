package com.irealmar.service;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.irealmar.dbaccess.Client;
import com.irealmar.dbaccess.ClientDDBB;
import com.irealmar.service.impl.WithdrawalService;

/**
 * TODO: documentar.
 */
@RunWith(PowerMockRunner.class)
//TODO: make this work. balnce service is null ¿?
// @PrepareForTest({ClientDDBB.class, IBalanceService.class})
@PrepareForTest(ClientDDBB.class)
// @PrepareForTest({ClientDDBB.class, WithdrawalService.class})
public class WithdrawalServiceTest {

    @Mock
    private ClientDDBB clientDDBB;

    @Mock
    private IBalanceService balanceService;

    private WithdrawalService withdrawalService;

    /**
     * Prepare tests.
     */
    @Before
    public void setUp() {
        withdrawalService = new WithdrawalService();
    }

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
        boolean accountExists = true;

        mockStatic(ClientDDBB.class);

        expect(balanceService.checkBalance(pin, accountNumber)).andReturn(actualBalance);
        expect(clientDDBB.accountExists(accountNumber)).andReturn(accountExists);
        expect(clientDDBB.getClient(accountNumber)).andReturn(client);

        replayAll();
        Double maximumWithdrawal = withdrawalService.getMaximumWithdrawal(pin, Long.valueOf(accountNumber));
        verifyAll();
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
    // Long accountNumber = Long.valueOf(123456780);
    // int pin = 1234;
    // mockStatic(ClientDDBB.class);
    //
    // Mockito.when(balanceService.checkBalance(pin, accountNumber)).thenReturn(500.0);
    //
    // expect(ClientDDBB.accountExists(accountNumber)).andReturn(false);
    // expect(ClientDDBB.getClient(accountNumber)).andReturn(null);
    //
    // replayAll();
    // withdrawalService.getMaximumWithdrawal(pin, Long.valueOf(11111111));
    //
    // }
}
