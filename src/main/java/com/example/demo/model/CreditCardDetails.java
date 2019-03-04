package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class CreditCardDetails {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Size(max=19, message="Card number should have at most 19 characters")
    private String cardNumber;

    private double balance;

    @NotNull
    private double cardLimit;

    public CreditCardDetails(){

    }

    public CreditCardDetails(String name, String cardNumber, double balance, double limit) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.cardLimit = limit;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(double cardLimit) {
        this.cardLimit = cardLimit;
    }



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardDetails that = (CreditCardDetails) o;
        return id == that.id &&
                Double.compare(that.balance, balance) == 0 &&
                Double.compare(that.cardLimit, cardLimit) == 0 &&
                name.equals(that.name) &&
                cardNumber.equals(that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cardNumber, balance, cardLimit);
    }
}
