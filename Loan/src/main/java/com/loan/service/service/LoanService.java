package com.loan.service.service;

import com.loan.service.entity.Loan;

import java.util.List;

public interface LoanService {

    public Loan createLoan(Loan loan);

    public Loan getLoan(int id);

    public List<Loan> listOfLoans();

    public Loan updateLoan(Loan loan);

    public void deleteLoanById(int id);

    public List<Loan> findByUserIdListOfLoans(int userId);
}
