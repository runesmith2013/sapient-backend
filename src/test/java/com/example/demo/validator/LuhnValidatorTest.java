package com.example.demo.validator;


import com.example.demo.DemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LuhnValidatorTest {


    @Autowired
    private LuhnValidator validator;

    @Test
    public void testCorrectCardNumbersPass() {
        boolean isValid =  validator.luhn("1111222233334444");

        Assert.assertTrue("Credit card number should be valid", isValid);

        isValid =  validator.luhn("4444333322221111");

        Assert.assertTrue("Credit card number should be valid", isValid);

    }

    @Test
    public void testInCorrectCardNumbersFail() {
        boolean isValid =  validator.luhn("1122233334444");

        Assert.assertFalse("Credit card number Not should be valid", isValid);

        isValid =  validator.luhn("444433332222111");

        Assert.assertFalse("Credit card number should be valid", isValid);

    }

}
