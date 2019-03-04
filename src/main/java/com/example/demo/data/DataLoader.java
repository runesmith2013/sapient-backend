package com.example.demo.data;


import com.example.demo.model.CreditCardDetails;
import com.example.demo.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private CreditCardRepository repository;


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        repository.save(new CreditCardDetails("Bob", "12345", 123, 100));
        repository.save(new CreditCardDetails("Alice", "12345", 123, 100));

    }
}
