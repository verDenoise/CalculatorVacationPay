package com.neostudy.calculatorvacationpay;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VacationPayControllerTest extends CommonTest{

    private final Double averageSalary = 100000d;
    @SuppressWarnings("FieldCanBeLocal")
    private final Integer quantityVacationDays = 10;
    private final LocalDate dateStartOfVacation = LocalDate.parse(LocalDate.of(2025, 5, 1).format(DateTimeFormatter.ISO_LOCAL_DATE));
    private final LocalDate dateEndOfVacation = LocalDate.parse(LocalDate.of(2025, 5, 9).format(DateTimeFormatter.ISO_LOCAL_DATE));

    @Order(1)
    @Test
    void getInfoCalculateVacationPay() throws Exception {
        String result = mvc.perform(
                        get("/calculate").headers(headers))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    @Order(2)
    @Test
    void calculateVacationPayByAverageSalaryAndQuantityVacationDays() throws Exception {
        String result = mvc.perform(
                        get("/calculate?averageSalary={averageSalary}&quantityVacationDays={quantityVacationDays}",
                                averageSalary, quantityVacationDays).headers(headers))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    @Order(3)
    @Test
    void calculateVacationPayByAverageSalaryAndDateOfVacationDays() throws Exception {
        String result = mvc.perform(
                        get("/calculate?averageSalary={averageSalary}&dateStartOfVacation={dateStartOfVacation}&dateEndOfVacation={dateEndOfVacation}",
                                averageSalary, dateStartOfVacation, dateEndOfVacation).headers(headers))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

}
