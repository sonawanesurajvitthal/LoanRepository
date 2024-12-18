package com.loan.service.controller;

import com.loan.service.constants.ApiConstants;
import com.loan.service.dto.LoanDTO;
import com.loan.service.entity.Loan;
import com.loan.service.exception.LoanRequiredFields;
import com.loan.service.service.LoanService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(ApiConstants.LOAN_CONTROLLER)
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Operation(
            tags = "GET LOAN",
            description = "Get LOAN by using Loan id.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Data Not Found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoan(@PathVariable("id") int id){
        Loan loan = loanService.getLoan(id);
        LoanDTO loanDTO = new LoanDTO().convertLoanIntoDTO(loan);
        return ResponseEntity.status(HttpStatus.OK).body(loanDTO);
    }

    public static String uploadDirectory = System.getProperty("user.dir") + "src/main/webapp/images";

    @Operation(
            tags = "CREATE LOAN",
            description = "Create Loan.",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<LoanDTO> saveLoan(@ModelAttribute Loan loan, @RequestParam("file") MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory,originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        loan.setFile(originalFilename);
        LoanDTO loanDTO = new LoanDTO().convertLoanIntoDTO(loanService.createLoan(loan));

        return ResponseEntity.status(HttpStatus.CREATED).body(loanDTO);
    }

    @Operation(
            tags = "UPDATE LOAN",
            description = "Update Loan.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400"
                    )
            }
    )
    @PutMapping
    public ResponseEntity<LoanDTO> updateLoan(@RequestBody Loan loan){
        if(loan.getId() == 0){
            throw new LoanRequiredFields(ApiConstants.REQUIRED_FIELD_ID);
        }
        LoanDTO loanDTO = new LoanDTO().convertLoanIntoDTO(loanService.updateLoan(loan));
        return ResponseEntity.status(HttpStatus.CREATED).body(loanDTO);
    }

    @Operation(
            tags = "GET ALL LOANS",
            description = "Get All LOANS.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),

            }
    )
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

    @Operation(
            tags = "DELETE LOAN",
            description = "Delete LOAN by using loan id.",
            responses = {
                    @ApiResponse(
                            description = "Deleted",
                            responseCode = "203"
                    ),
                    @ApiResponse(
                            description = "Data Not Found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable int id){
        loanService.deleteLoanById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiConstants.DELETE_SUCCESSFULLY+id);
    }


}
