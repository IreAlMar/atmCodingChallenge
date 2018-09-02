package com.irealmar.controller;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irealmar.service.IWithdrawalService;
import com.irealmar.service.InvalidAccountException;
import com.irealmar.service.InvalidPinException;

/**
 * TODO: control invalid request params.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/atm/withdrawal")
public class WithdrawalController {
    @Autowired
    IWithdrawalService withdrawalService;

    /**
     * TODO: documentar.
     * @param pin
     *        pin number
     * @param accountNumber
     *        account number
     * @return Response with the maximum withdrawal if the operation was successful and the result code of the operation
     *         to be interpreted by the client.
     */
    @RequestMapping("/getMaxWithdrawal")
    public ResponseEntity<MaxWithdrawalResponse> getMaxWithdrawal(@RequestParam(defaultValue = "-1") int pin,
        @RequestParam(defaultValue = "-1") Long accountNumber) {
        MaxWithdrawalResponse maxWithdrawalResponse = null;

        try {
            maxWithdrawalResponse = new MaxWithdrawalResponse(withdrawalService.getMaximumWithdrawal(pin,
                accountNumber), "OK");
        } catch (InvalidPinException ex) {
            // TODO: use exception handler
            maxWithdrawalResponse = new MaxWithdrawalResponse(0, "Invalid pin");
        } catch (InvalidAccountException ex) {
            maxWithdrawalResponse = new MaxWithdrawalResponse(0, "Invalid account");
        }

        return new ResponseEntity<>(maxWithdrawalResponse, HttpStatus.OK);
    }

    /**
     * TODO: documentar.
     * @param pin
     *        pin number
     * @param accountNumber
     *        account number
     * @param amount
     *        the requested amount
     * @return Response with the maximum withdrawal if the operation was successful and the result code of the operation
     *         to be interpreted by the client.
     */
    @RequestMapping("/getWithdrawal")
    public ResponseEntity<DispenseResponse> dispenseFunds(@RequestParam(defaultValue = "-1") int pin,
        @RequestParam(defaultValue = "-1") Long accountNumber, @RequestParam(defaultValue = "-1") int amount) {
        DispenseResponse dispenseResponse = null;

        try {
            dispenseResponse = new DispenseResponse(withdrawalService.dispenseFunds(pin, accountNumber), "OK");
        } catch (InvalidPinException ex) {
            // TODO: use exception handler
            dispenseResponse = new DispenseResponse(new TreeMap<Integer, Integer>(), "Invalid pin");
        } catch (InvalidAccountException ex) {
            dispenseResponse = new DispenseResponse(new TreeMap<Integer, Integer>(), "Invalid account");
        }

        return new ResponseEntity<>(dispenseResponse, HttpStatus.OK);
    }

}
