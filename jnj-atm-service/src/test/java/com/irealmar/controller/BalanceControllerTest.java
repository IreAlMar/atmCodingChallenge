package com.irealmar.controller;

import static org.junit.Assert.assertEquals;
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
 * Test class for BalanceController.
 */
@RunWith(MockitoJUnitRunner.class)
public class BalanceControllerTest {
    @Mock
    IBalanceService balanceService;

    @InjectMocks
    private BalanceController balanceController;

    /**
     * Successful balance check.
     */
    @Test
    public void getBalanceOKTest() {
        try {
            Mockito.when(balanceService.checkBalance(1234, Long.valueOf(20182018))).thenReturn(500.0);
        } catch (InvalidPinException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (com.irealmar.service.InvalidAccountException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
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
        try {
            Mockito.when(balanceService.checkBalance(-1234, Long.valueOf(20182018))).thenThrow(
                new InvalidPinException());
        } catch (InvalidPinException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (com.irealmar.service.InvalidAccountException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
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
        try {
            Mockito.when(balanceService.checkBalance(1234, Long.valueOf(-20182018))).thenThrow(
                new InvalidAccountException());
        } catch (InvalidPinException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (com.irealmar.service.InvalidAccountException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        BalanceResponse balanceResult = new BalanceResponse(0, "Invalid account");
        ResponseEntity<BalanceResponse> spectedResponse = new ResponseEntity<>(balanceResult, HttpStatus.OK);
        ResponseEntity<BalanceResponse> response = balanceController.getBalance(1234, Long.valueOf(-20182018));

        assertEquals(spectedResponse.getBody().getBalance(), response.getBody().getBalance(), 0.0);
        assertEquals(spectedResponse.getBody().getResultCode(), response.getBody().getResultCode());
        assertEquals(spectedResponse.getStatusCode(), response.getStatusCode());
    }

}
