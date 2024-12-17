package com.loan.service.service;

import com.loan.service.constants.ApiConstants;
import com.loan.service.entity.Loan;
import com.loan.service.exception.LoanNotFoundException;
import com.loan.service.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRepositoryImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan createLoan(Loan loan) {

        if(loan.getName() == null){
            throw new LoanNotFoundException(ApiConstants.REQUIRED_FIELD);
        }
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoan(int id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(ApiConstants.LOAN_NOT_FOUND_ID+id));
    }

    @Override
    public List<Loan> listOfLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan updateLoan(Loan loan) {

        Loan loanTemp = loanRepository.findById(loan.getId())
                .orElseThrow(()-> new LoanNotFoundException(ApiConstants.LOAN_NOT_FOUND_ID+loan.getId()));

        if(loan.getName() ==  null){
            loan.setName(loanTemp.getName());
        }
        if(loan.getAmount() ==  0){
            loan.setAmount(loanTemp.getAmount());
        }

        return loanRepository.save(loan);
    }

    @Override
    public void deleteLoanById(int id) {
        loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(ApiConstants.LOAN_NOT_FOUND_ID+id));
        loanRepository.deleteById(id);

    }
}
