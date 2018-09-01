package com.irealmar.dbaccess;

import java.util.Collections;
import java.util.TreeMap;

/**
 * TODO: documentar.
 */
public class AtmNotes {
    private static AtmNotes atmNotes;

    // Map <NoteType, amount of available notes>
    private TreeMap<Integer, Integer> notes;

    // The ATM should initialize with €2000 made up of 20 x €50s, 30 x €20s, 30 x €10s and 20 x €5s
    private AtmNotes() {
        notes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        notes.put(50, 20);
        notes.put(20, 30);
        notes.put(10, 30);
        notes.put(5, 20);
    }

    /**
     * TODO: documentar.
     * @return the AtmNotes instance
     */
    public static AtmNotes getAtmNotesInstance() {
        if (atmNotes == null) {
            atmNotes = new AtmNotes();
        }
        return atmNotes;
    }

    private Integer getAtmTotalAmount() {
        return notes.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();
    }

    /**
     * TODO: documentar.
     * @param withdrawalAmount
     *        amount requested
     * @return list of notes
     */
    public TreeMap<Integer, Integer> isAmountAvailable(int withdrawalAmount) {
        TreeMap<Integer, Integer> remain = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        remain.putAll(notes);
        TreeMap<Integer, Integer> result = new TreeMap<Integer, Integer>(Collections.reverseOrder());

        if (getAtmTotalAmount() >= withdrawalAmount) {
            result = calculateWitdrawalNotes(withdrawalAmount, remain, result);
        }

        return result;

    }

    private TreeMap<Integer, Integer> calculateWitdrawalNotes(int withdrawalAmount, TreeMap<Integer, Integer> remain,
        TreeMap<Integer, Integer> result) {
        Integer withdrawalCandidate = result.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();

        if (withdrawalAmount > withdrawalCandidate && !remain.isEmpty()) {

            if (remain.firstEntry().getValue() > 0 && withdrawalAmount >= remain.firstKey()) {

                result.put(remain.firstKey(), result.firstEntry().getValue() + 1);
                remain.put(remain.firstKey(), remain.firstEntry().getValue() - 1);
                withdrawalAmount -= result.firstKey();
            } else {
                remain.remove(remain.firstKey());
                return calculateWitdrawalNotes(withdrawalAmount, remain, result);
            }
        } else {
            return result;
        }
        return result;
    }

}
