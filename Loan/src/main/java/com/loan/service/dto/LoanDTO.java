package com.loan.service.dto;

import com.loan.service.entity.Loan;

public class LoanDTO {

    private int id;

    private String name;

    private double amount;

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LoanDTO convertLoanIntoDTO(Loan loan){
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setName(loan.getName());
        loanDTO.setAmount(loan.getAmount());
        loanDTO.setUserId(loan.getUserId());
        return loanDTO;
    }
}
