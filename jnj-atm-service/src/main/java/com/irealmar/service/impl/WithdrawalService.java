package com.irealmar.service.impl;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.irealmar.repository.CashContainer;
import com.irealmar.repository.Client;
import com.irealmar.repository.ClientContainer;
import com.irealmar.repository.TransactionContainer;
import com.irealmar.service.IBalanceService;
import com.irealmar.service.IWithdrawalService;
import com.irealmar.service.WithdrawalResult;

/**
 * Service for withdrawal operations.
 */
@Service
public class WithdrawalService implements IWithdrawalService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IBalanceService balanceService;

    private ClientContainer clientContainer;
    private CashContainer cashContainer;
    private TransactionContainer transactionContainer;

    /**
     * Calculate maximum withdrawal possible counting with the ATM available notes.
     * @param pin
     *        the pin number
     * @param accountNumber
     *        the account number corresponding to the pin number
     * @return maximum withdrawal amount
     * @throws InvalidPinException
     *         Exception thrown when the pin is invalid
     * @throws InvalidAccountException
     *         Exception thrown when the account is invalid
     */
    @Override
    public Double getMaximumWithdrawal(int pin, Long accountNumber) throws InvalidPinException,
        InvalidAccountException {
        transactionContainer = (TransactionContainer)context.getBean("transactionContainer");
        clientContainer = (ClientContainer)context.getBean("clientContainer");
        cashContainer = (CashContainer)context.getBean("cashContainer");
        Double overdraft = 0.0;
        Double clientBalance = balanceService.checkBalance(pin, accountNumber);
        Double result = 0.0;

        // TODO: change to avoid duplicated code from BalanceService.java, use AOP for check pin and account
        if (!clientContainer.accountExists(accountNumber)) {
            throw new InvalidAccountException();

        } else {
            Client client = clientContainer.getClient(accountNumber);
            if (client != null && client.getPin() != pin) {
                throw new InvalidPinException();

            } else if (client != null) {
                overdraft = client.getOverdraft();
                TreeMap<Integer, Integer> withdrawalNotes = cashContainer.calculateWithdrawal(new Double(clientBalance
                    + overdraft).intValue());
                result = new Double(withdrawalNotes.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum());
            }
        }

        return result;
    }

    /**
     * Perform a withdrawal. This operation involves verification of the pin and account, verification of the available
     * client' s funds and verify that there are enough notes in the ATM. The service will only dispense the exact
     * amounts requested, if the notes in the ATM do not fit the amount requested the withdrawal will no be performed.
     * @param pin
     *        the pin number
     * @param accountNumber
     *        the account number corresponding to the pin number
     * @return the details of the notes that would be dispensed along with the remaining balance
     */
    @Override
    public WithdrawalResult dispenseFunds(int pin, Long accountNumber, int amount)
        throws InvalidAccountException,
        InvalidPinException, InsuficientFundsException, InsuficientCashException, UnavailableAmountException {

        transactionContainer = (TransactionContainer)context.getBean("transactionContainer");
        clientContainer = (ClientContainer)context.getBean("clientContainer");
        cashContainer = (CashContainer)context.getBean("cashContainer");
        boolean amountIsAvailable = false;
        TreeMap<Integer, Integer> withdrawalNotes = null;

        if (!clientContainer.accountExists(accountNumber)) {
            throw new InvalidAccountException();

        } else {
            Client client = clientContainer.getClient(accountNumber);
            if (client != null && client.getPin() != pin) {
                throw new InvalidPinException();

            } else if (client != null) {
                Double clientBalance = balanceService.checkBalance(pin, accountNumber);

                if (amount > (clientBalance + client.getOverdraft())) {
                    throw new InsuficientFundsException();

                } else if (amount > cashContainer.getTotalCash()) {
                    throw new InsuficientCashException();

                } else {
                    withdrawalNotes = cashContainer.calculateWithdrawal(amount);
                    amountIsAvailable = amount == withdrawalNotes.entrySet().stream().mapToInt(e -> e.getKey() * e
                        .getValue()).sum();

                    if (!amountIsAvailable) {
                        throw new UnavailableAmountException();

                    } else {
                        transactionContainer.addTransaction(accountNumber, new Double(-amount));
                        cashContainer.dispenseNotes(withdrawalNotes);
                        return new WithdrawalResult(withdrawalNotes, balanceService.checkBalance(pin, accountNumber));
                    }
                }
            }
            return new WithdrawalResult();
        }
    }

}
