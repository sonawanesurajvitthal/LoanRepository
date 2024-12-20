package com.loan.service.constants;

public class ApiConstants {
    // API Endpoints
    public static final String GET_LOANS = "/list";
    public static final String LOAN_CONTROLLER = "/api/v1/loan";


    // Other Constants
    public static final String DELETE_SUCCESSFULLY = "Delete Loan successfully against Id: ";
    public static final String IS_ACTIVE = "ACTIVE";
    public static final String DEACTIVATE = "DEACTIVATE";
    public static final String NOT_ACTIVATE_LOAN = "Not Activate Loan ID: ";

    //Exception Constants
    public static final String LOAN_NOT_FOUND_ID = "LOAN NOT FOUND AGAINST LOAN ID: ";
    public static final String LOAN_NOT_FOUND = "LOAN NOT FOUND";
    public static final String REQUIRED_FIELD =  "Field cannot be null:";
    public static final String REQUIRED_FIELD_ID =  "ID Field cannot be null:";

}
