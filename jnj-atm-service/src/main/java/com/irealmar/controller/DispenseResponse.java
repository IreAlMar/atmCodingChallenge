package com.irealmar.controller;

import com.irealmar.service.WithdrawalResult;

public class DispenseResponse {

    private WithdrawalResult withdrawalResult;
    private String resultCode;

    public DispenseResponse(WithdrawalResult withdrawalResult, String resultCode) {
        super();
        this.withdrawalResult = withdrawalResult;
        this.resultCode = resultCode;
    }

    public WithdrawalResult getWithdrawalResult() {
        return withdrawalResult;
    }

    public void setWithdrawalResult(WithdrawalResult withdrawalResult) {
        this.withdrawalResult = withdrawalResult;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

}
