package com.loan.service.exception;

import com.loan.service.constants.ApiConstants;

public class LoanNotFoundException extends RuntimeException{

    public LoanNotFoundException(){
        super(ApiConstants.LOAN_NOT_FOUND);
    }

    public LoanNotFoundException(String message){
        super(message);
    }
}
