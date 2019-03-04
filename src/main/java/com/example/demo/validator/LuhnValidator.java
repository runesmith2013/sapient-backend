package com.example.demo.validator;

import org.springframework.stereotype.Component;

@Component
public class LuhnValidator {

    public boolean luhn(String cc) {
        final boolean[] dbl = {false};
        return cc
                .chars()
                .map(c -> Character.digit((char) c, 10))
                .map(i -> ((dbl[0] = !dbl[0])) ? (((i*2)>9) ? (i*2)-9 : i*2) : i)
                .sum() % 10 == 0;
    }

}
