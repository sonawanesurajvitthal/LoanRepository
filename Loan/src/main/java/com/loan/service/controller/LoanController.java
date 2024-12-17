package com.loan.service.controller;

import com.loan.service.constants.ApiConstants;
import com.loan.service.dto.LoanDTO;
import com.loan.service.entity.Loan;
import com.loan.service.exception.LoanRequiredFields;
import com.loan.service.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.LOAN_CONTROLLER)
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoan(@PathVariable int id){
        Loan loan = loanService.getLoan(id);
        LoanDTO loanDTO = new LoanDTO().convertLoanIntoDTO(loan);
        return ResponseEntity.status(HttpStatus.OK).body(loanDTO);
    }

    @PostMapping
    public ResponseEntity<LoanDTO> saveLoan(@RequestBody Loan loan){

        LoanDTO loanDTO = new LoanDTO().convertLoanIntoDTO(loanService.createLoan(loan));

        return ResponseEntity.status(HttpStatus.CREATED).body(loanDTO);
    }

    @PutMapping
    public ResponseEntity<LoanDTO> updateLoan(@RequestBody Loan loan){
        if(loan.getId() == 0){
            throw new LoanRequiredFields(ApiConstants.REQUIRED_FIELD_ID);
        }
        LoanDTO loanDTO = new LoanDTO().convertLoanIntoDTO(loanService.updateLoan(loan));
        return ResponseEntity.status(HttpStatus.CREATED).body(loanDTO);
    }

    @GetMapping(ApiConstants.GET_LOANS)
    public ResponseEntity<List<LoanDTO>> getAllLoan(){
        List<Loan> list = loanService.listOfLoans();
        List<LoanDTO> listLoanDTO = new ArrayList<>();
        for(Loan loan : list){
            LoanDTO loanDTO = new LoanDTO().convertLoanIntoDTO(loan);
            listLoanDTO.add(loanDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(listLoanDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable int id){
        loanService.deleteLoanById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiConstants.DELETE_SUCCESSFULLY+id);
    }


}
