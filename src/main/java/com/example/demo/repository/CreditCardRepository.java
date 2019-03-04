package com.example.demo.repository;

import com.example.demo.model.CreditCardDetails;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepository extends CrudRepository<CreditCardDetails, Integer> {
}
