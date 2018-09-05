package com.irealmar.response;

import com.irealmar.service.impl.WithdrawalResult;

/**
 * Response for withdrawal operations.
 */
public class DispenseResponse {

    private WithdrawalResult withdrawalResult;
    private String resultCode;

    /**
     * Constructor.
     * @param withdrawalResult
     *        the withdrawal result
     * @param resultCode
     *        the result code
     */
    public DispenseResponse(WithdrawalResult withdrawalResult, String resultCode) {
        super();
        this.withdrawalResult = withdrawalResult;
        this.resultCode = resultCode;
    }

    /**
     * Getter.
     * @return the withdrawal result
     */
    public WithdrawalResult getWithdrawalResult() {
        return withdrawalResult;
    }

    /**
     * Setter.
     * @param withdrawalResult
     *        the withdrawal result
     */
    public void setWithdrawalResult(WithdrawalResult withdrawalResult) {
        this.withdrawalResult = withdrawalResult;
    }

    /**
     * Getter.
     * @return the result code
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Setter.
     * @param resultCode
     *        the result code
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

}
