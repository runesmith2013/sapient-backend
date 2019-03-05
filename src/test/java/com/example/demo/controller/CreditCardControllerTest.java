package com.example.demo.controller;


import com.example.demo.model.CreditCardDetails;
import com.example.demo.repository.CreditCardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreditCardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreditCardRepository repository;



    @Test
    public void testRetrievingCreditCardDetailsPasses() throws Exception {


        List<CreditCardDetails>  cardDetailsList = new ArrayList();
        cardDetailsList.add(new CreditCardDetails("Bob","12345",123.0,100.0));

        when(repository.findAll()).thenReturn(cardDetailsList);


        this.mvc.perform(get("/carddetails"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':0,'name':'Bob','cardNumber':'12345','balance':123.0,'cardLimit':100.0}]"));

        //-- TODO: convert to JSON path testing to allow finer grained control
    }

    @Test
    public void testCreatingNewRecordWithCorrectValuesPasses() throws Exception {

        CreditCardDetails details = new CreditCardDetails("Name","1111222233334444",0,100);

        mvc.perform(MockMvcRequestBuilders.
                post("/creditcard")
                .content(convertObjectToJsonString(details))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); //-- should this return 201 instead?

    }


    @Test
    public void testCreatingNewRecordWithInvalidCardNumberReturnsCorrectErrorMessage() throws Exception {

        CreditCardDetails details = new CreditCardDetails("Name","cardnumber",0,100);

        mvc.perform(MockMvcRequestBuilders.
                post("/creditcard")
                .content(convertObjectToJsonString(details))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(content().json("{'message':'Card number is invalid'}"));


    }



    //TODO: Test validation on name, card number length
    //TODO: Test edge cases: null, negative, empty string etc
    //TODO: Test system failure cases: database unresponsive etc




    //Converts Object to Json String
    private String convertObjectToJsonString(CreditCardDetails details) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(details);

    }


}
