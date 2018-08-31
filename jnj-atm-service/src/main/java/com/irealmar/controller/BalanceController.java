package com.irealmar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irealmar.service.IBalanceService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: documentar.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/atm/balance")
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
    @RequestMapping("/getBalance")
    public ResponseEntity<BalanceResponse> getBalance(@RequestParam(defaultValue = "-1") int pin, @RequestParam(
        defaultValue = "-1") Long accountNumber) {
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
