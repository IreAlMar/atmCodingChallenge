package com.irealmar.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.irealmar.service.IBalanceService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
@RunWith(MockitoJUnitRunner.class)
public class BalanceControllerTest {
    @Mock
    IBalanceService balanceService;

    @InjectMocks
    private BalanceController balanceController;

    /**
     * Prepare test mock.
     * @throws InvalidPinException
     *         exception thrown in case of invalid pin
     * @throws InvalidAccountException
     *         exception thrown in case of invalid account number
     */
    @Before
    public void prepareMocks() throws InvalidPinException, InvalidAccountException {
        Mockito.when(balanceService.checkBalance(Mockito.intThat(x -> x > 0), Mockito
            .longThat(x -> x > 0))).thenReturn(500.0);
        Mockito.when(balanceService.checkBalance(Mockito.intThat(x -> x <= 0), Mockito.anyLong())).thenThrow(
            new InvalidPinException());
        Mockito.when(balanceService.checkBalance(Mockito.anyInt(), Mockito.longThat(x -> x <= 0)))
            .thenThrow(
                new InvalidAccountException());
    }

    /**
     * Successful balance check.
     */
    @Test
    public void getBalanceOKTest() {
        BalanceResponse balanceResult = new BalanceResponse(500.0, "OK");
        ResponseEntity<BalanceResponse> spectedResponse = new ResponseEntity<>(balanceResult, HttpStatus.OK);
        ResponseEntity<BalanceResponse> response = balanceController.getBalance(1234, Long.valueOf(20182018));

        assertEquals(spectedResponse.getBody().getBalance(), response.getBody().getBalance(), 0.0);
        assertEquals(spectedResponse.getBody().getResultCode(), response.getBody().getResultCode());
        assertEquals(spectedResponse.getStatusCode(), response.getStatusCode());
    }

    /**
     * Invalid pin balance check.
     */
    @Test
    public void getBalanceInvalidAccountTest() {
        BalanceResponse balanceResult = new BalanceResponse(0, "Invalid pin");
        ResponseEntity<BalanceResponse> spectedResponse = new ResponseEntity<>(balanceResult, HttpStatus.OK);
        ResponseEntity<BalanceResponse> response = balanceController.getBalance(-1234, Long.valueOf(20182018));

        assertEquals(spectedResponse.getBody().getBalance(), response.getBody().getBalance(), 0.0);
        assertEquals(spectedResponse.getBody().getResultCode(), response.getBody().getResultCode());
        assertEquals(spectedResponse.getStatusCode(), response.getStatusCode());
    }

    /**
     * Invalid account balance check.
     */
    @Test
    public void InvalidAccountException() {

        BalanceResponse balanceResult = new BalanceResponse(0, "Invalid account");
        ResponseEntity<BalanceResponse> spectedResponse = new ResponseEntity<>(balanceResult, HttpStatus.OK);
        ResponseEntity<BalanceResponse> response = balanceController.getBalance(1234, Long.valueOf(-20182018));

        assertEquals(spectedResponse.getBody().getBalance(), response.getBody().getBalance(), 0.0);
        assertEquals(spectedResponse.getBody().getResultCode(), response.getBody().getResultCode());
        assertEquals(spectedResponse.getStatusCode(), response.getStatusCode());
    }

}
