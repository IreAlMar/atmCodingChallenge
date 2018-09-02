package com.irealmar.controller;

import java.util.TreeMap;

/**
 * TODO: documentar.
 */
public class DispenseResponse {

    TreeMap<Integer, Integer> withdrawalNotes;
    private String resultCode;

    /**
     * TODO: documentar.
     * @param withdrawalNotes
     *        the withdrawalNotes
     * @param resultCode
     *        code result for the operation requested
     */
    public DispenseResponse(TreeMap<Integer, Integer> withdrawalNotes, String resultCode) {
        super();
        this.withdrawalNotes = withdrawalNotes;
        this.resultCode = resultCode;
    }

}
