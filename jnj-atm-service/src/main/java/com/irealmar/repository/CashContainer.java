package com.irealmar.repository;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Cash store.
 */
public class CashContainer {

    // Map <NoteType, amount of available notes>
    private TreeMap<Integer, Integer> notes;

    /**
     * Constructor. The ATM should initialize with €2000 made up of 20 x €50s, 30 x €20s, 30 x €10s and 20 x €5s
     */
    public CashContainer() {
        notes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        notes.put(50, 20);
        notes.put(20, 30);
        notes.put(10, 30);
        notes.put(5, 20);
    }

    /**
     * Get total cash in the ATM.
     * @return Total Cash
     */
    public Integer getTotalCash() {
        return notes.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();
    }

    /**
     * Calculates the notes available for a withdrawal amount. It uses the recursive function
     * {@link #calculateWitdrawalNotes}
     * @param withdrawalAmount
     *        amount requested
     * @return list of notes
     */
    public TreeMap<Integer, Integer> calculateWithdrawal(int withdrawalAmount) {
        TreeMap<Integer, Integer> remain = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        remain.putAll(notes);
        TreeMap<Integer, Integer> result = new TreeMap<Integer, Integer>(Collections.reverseOrder());

        if (getTotalCash() >= withdrawalAmount) {
            result = calculateWitdrawalNotes(withdrawalAmount, remain, result);
        }

        return result;

    }

    /**
     * Auxiliary function for {@link #calculateWitdrawal}. The algorithm consists of an iteration over a copy of the
     * existing notes in the ATM ordered from greater to smaller amount and using the tail of the map when the greater
     * note value is useless.
     * @param withdrawalAmount
     *        amount requested
     * @return list of notes
     */
    private TreeMap<Integer, Integer> calculateWitdrawalNotes(int withdrawalAmount, TreeMap<Integer, Integer> remain,
        TreeMap<Integer, Integer> result) {

        if (remain.isEmpty()) {
            return result;
        }

        Integer currentNoteValue = remain.firstKey();
        Integer currentNoteUnits = remain.get(currentNoteValue);

        if (withdrawalAmount < remain.lastEntry().getKey()) {
            return result;
        }

        if (currentNoteUnits > 0 && withdrawalAmount >= currentNoteValue) {
            result.put(currentNoteValue, (result.containsKey(currentNoteValue) ? result.get(currentNoteValue) : 0) + 1);
            remain.put(currentNoteValue, remain.get(currentNoteValue) - 1);
            withdrawalAmount -= currentNoteValue;
            return calculateWitdrawalNotes(withdrawalAmount, remain, result);
        } else {
            remain.remove(currentNoteValue);
            return calculateWitdrawalNotes(withdrawalAmount, remain, result);
        }

    }

    /**
     * Update the ATM cash when a group of notes are dispensed.
     * @param dispensedNotes
     *        the dispensed notes
     */
    public void dispenseNotes(TreeMap<Integer, Integer> dispensedNotes) {
        for (Map.Entry<Integer, Integer> entry : dispensedNotes.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            // dispensedNotes.compute(key, notes -> notes.put(key, notes.getKey() - dispensedNotes.getKey()));
            notes.put(key, notes.get(key) - value);
        }
    }

}
