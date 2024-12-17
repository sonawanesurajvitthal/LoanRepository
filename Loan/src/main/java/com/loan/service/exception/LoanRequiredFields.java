package com.loan.service.exception;

import com.loan.service.constants.ApiConstants;

public class LoanRequiredFields extends RuntimeException{

    public LoanRequiredFields(){
        super(ApiConstants.REQUIRED_FIELD);
    }

    public LoanRequiredFields(String message){
        super(message);
    }
}
