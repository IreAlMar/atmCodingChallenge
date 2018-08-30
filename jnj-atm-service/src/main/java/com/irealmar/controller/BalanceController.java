package com.irealmar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.irealmar.service.IBalanceService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
public class BalanceController {
    @Autowired
    private IBalanceService balanceService;

    /**
     * TODO: documentar.
     * @param pin
     *        pin number
     * @param accountNumber
     *        account number
     * @return Response with the balance if the operation was successful and the result code of the operation to be
     *         interpreted by the client.
     */
    public ResponseEntity<BalanceResponse> getBalance(int pin, Long accountNumber) {
        BalanceResponse balanceResponse = null;

        try {
            balanceResponse = new BalanceResponse(balanceService.checkBalance(pin, accountNumber), "OK");
        } catch (InvalidPinException ex) {
            // TOTHINK: which value return when invalid pin
            balanceResponse = new BalanceResponse(0, "Invalid pin");
        } catch (InvalidAccountException ex) {
            // TOTHINK: which value return when invalid account
            balanceResponse = new BalanceResponse(0, "Invalid account");
        }

        return new ResponseEntity<>(balanceResponse, HttpStatus.OK);
    }

}
