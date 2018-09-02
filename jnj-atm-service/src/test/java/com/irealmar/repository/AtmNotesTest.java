package com.irealmar.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * TODO: documentar.
 */
@RunWith(MockitoJUnitRunner.class)
public class AtmNotesTest {

    @InjectMocks
    private CashContainer cash;

    /**
     * TODO: documentar.
     */
    @Test
    public void itShouldInitializeWith2000() {
        assertEquals(Integer.valueOf(2000), cash.getTotalCash());
    }

    /**
     * TODO: pensar nombres de test.
     */
    @Test
    public void itShouldNotDisenseMoreMoneyThanItHolds() {
        TreeMap<Integer, Integer> actualWithdrawal = cash.calculateWithdrawal(3000);
        assertTrue(actualWithdrawal.isEmpty());
    }

    /**
     * TODO: pensar nombres de test.
     */
    @Test
    public void isAmountAvailableTest2() {
        TreeMap<Integer, Integer> actualWithdrawal = cash.calculateWithdrawal(100);
        assertEquals(Integer.valueOf(2), actualWithdrawal.get(50));
    }

    /**
     * TODO: pensar nombres de test.
     */
    @Test
    public void isAmountAvailableTest3() {
        TreeMap<Integer, Integer> actualWithdrawal = cash.calculateWithdrawal(103);
        assertEquals(Integer.valueOf(2), actualWithdrawal.get(50));
    }

    /**
     * TODO: pensar nombres de test.
     */
    @Test
    public void isAmountAvailableTest4() {
        TreeMap<Integer, Integer> actualWithdrawal = cash.calculateWithdrawal(99);
        assertEquals(Integer.valueOf(1), actualWithdrawal.get(50));
        assertEquals(Integer.valueOf(2), actualWithdrawal.get(20));
        assertEquals(Integer.valueOf(1), actualWithdrawal.get(5));
    }

    /**
     * TODO: pensar nombres de test.
     */
    @Test
    public void isAmountAvailableTest5() {
        TreeMap<Integer, Integer> actualWithdrawal = cash.calculateWithdrawal(3);
        assertTrue(actualWithdrawal.isEmpty());
    }

    /**
     * TODO: pensar nombres de test.
     */
    @Test
    public void isAmountAvailableTest6() {
        TreeMap<Integer, Integer> actualWithdrawal = cash.calculateWithdrawal(2000);
        assertEquals(Integer.valueOf(20), actualWithdrawal.get(50));
        assertEquals(Integer.valueOf(30), actualWithdrawal.get(20));
        assertEquals(Integer.valueOf(30), actualWithdrawal.get(10));
        assertEquals(Integer.valueOf(20), actualWithdrawal.get(5));
    }

    /**
     * TODO: test when the ATM is empty
     */
    // @Test
    // public void isAmountAvailableTest7() {
    // atmNotes.calculateWithdrawal(2000);
    // TreeMap<Integer, Integer> actualWithdrawal = atmNotes.calculateWithdrawal(2000);
    // assertTrue(actualWithdrawal.isEmpty());
    // }

}
