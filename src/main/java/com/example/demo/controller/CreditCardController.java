package com.example.demo.controller;


import com.example.demo.data.Result;
import com.example.demo.model.CreditCardDetails;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.validator.LuhnValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;


@RestController
public class CreditCardController {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private LuhnValidator validator;


    @GetMapping(value="/carddetails", produces="application/json")
    public Iterable<CreditCardDetails> getAll() {

        return creditCardRepository.findAll();
    }


    @PostMapping(value = "/creditcard", consumes = "application/json")
    public ResponseEntity<Object> add(@RequestBody @Valid CreditCardDetails creditCardDetails, Errors errors) {


        String cardNumber = creditCardDetails.getCardNumber();
        if (!validator.luhn(cardNumber)) {
            errors.reject("invalid.cardnumber", "Card number is invalid");
        }

        Result result = new Result();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            // get all errors
            result.setMsg(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        creditCardRepository.save(creditCardDetails);
        return ResponseEntity.ok("valid");
    }





}
