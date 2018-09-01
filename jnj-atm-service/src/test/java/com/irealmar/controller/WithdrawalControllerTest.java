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

import com.irealmar.service.IWithdrawalService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * Test class for .WithdrawalController.
 */
@RunWith(MockitoJUnitRunner.class)
public class WithdrawalControllerTest {
    @Mock
    IWithdrawalService withdrawalService;

    @InjectMocks
    private WithdrawalController withdrawalController;

    /**
     * Successful maximum withdrawal check.
     */
    @Test
    public void getMaximumWithdrawalSuceedTest() {
        try {
            Mockito.when(withdrawalService.getMaximumWithdrawal(1234, Long.valueOf(20182018))).thenReturn(1200.0);
        } catch (InvalidPinException ex) {
            ex.printStackTrace();
        } catch (InvalidAccountException ex) {
            ex.printStackTrace();
        }
        MaxWithdrawalResponse maxWithdrawalResult = new MaxWithdrawalResponse(1200.0, "OK");
        ResponseEntity<MaxWithdrawalResponse> spectedResponse = new ResponseEntity<>(maxWithdrawalResult,
            HttpStatus.OK);
        ResponseEntity<MaxWithdrawalResponse> response = withdrawalController.getMaxWithdrawal(1234, Long.valueOf(
            20182018));

        assertEquals(spectedResponse.getBody().getMaxWithdrawal(), response.getBody().getMaxWithdrawal(), 0.0);
        assertEquals(spectedResponse.getBody().getResultCode(), response.getBody().getResultCode());
        assertEquals(spectedResponse.getStatusCode(), response.getStatusCode());
    }

    /**
     * Invalid pin maximum withdrawal check.
     */
    @Test
    public void getMaximumWithdrawalInvalidAccountTest() {
        try {
            Mockito.when(withdrawalService.getMaximumWithdrawal(-1234, Long.valueOf(20182018))).thenThrow(
                new InvalidPinException());
        } catch (InvalidPinException ex) {
            ex.printStackTrace();
        } catch (InvalidAccountException ex) {
            ex.printStackTrace();
        }
        MaxWithdrawalResponse maxWithdrawalResult = new MaxWithdrawalResponse(0, "Invalid pin");
        ResponseEntity<MaxWithdrawalResponse> spectedResponse = new ResponseEntity<>(maxWithdrawalResult,
            HttpStatus.OK);
        ResponseEntity<MaxWithdrawalResponse> response = withdrawalController.getMaxWithdrawal(-1234, Long.valueOf(
            20182018));

        assertEquals(spectedResponse.getBody().getMaxWithdrawal(), response.getBody().getMaxWithdrawal(), 0.0);
        assertEquals(spectedResponse.getBody().getResultCode(), response.getBody().getResultCode());
        assertEquals(spectedResponse.getStatusCode(), response.getStatusCode());
    }

    /**
     * Invalid account maximum withdrawal check.
     */
    @Test
    public void getMaximumWithdrawalInvalidAccountExceptionTest() {
        try {
            Mockito.when(withdrawalService.getMaximumWithdrawal(1234, Long.valueOf(-20182018))).thenThrow(
                new InvalidAccountException());
        } catch (InvalidPinException ex) {
            ex.printStackTrace();
        } catch (InvalidAccountException ex) {
            ex.printStackTrace();
        }
        MaxWithdrawalResponse maxWithdrawalResult = new MaxWithdrawalResponse(0, "Invalid account");
        ResponseEntity<MaxWithdrawalResponse> spectedResponse = new ResponseEntity<>(maxWithdrawalResult,
            HttpStatus.OK);
        ResponseEntity<MaxWithdrawalResponse> response = withdrawalController.getMaxWithdrawal(1234, Long.valueOf(
            -20182018));

        assertEquals(spectedResponse.getBody().getMaxWithdrawal(), response.getBody().getMaxWithdrawal(), 0.0);
        assertEquals(spectedResponse.getBody().getResultCode(), response.getBody().getResultCode());
        assertEquals(spectedResponse.getStatusCode(), response.getStatusCode());
    }

}
