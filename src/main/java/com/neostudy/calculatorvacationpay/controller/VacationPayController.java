package com.neostudy.calculatorvacationpay.controller;

import com.neostudy.calculatorvacationpay.model.VacationPay;
import com.neostudy.calculatorvacationpay.service.VacationPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class VacationPayController {

    @Autowired
    private VacationPayService vacationPayService;

    @GetMapping("/calculate")
    public String VacationPayCalculate() {
        return vacationPayService.vacationPayCalculating();
    }

    @GetMapping(value = "/calculate", params = {"averageSalary", "quantityVacationDays"})
    public String VacationPayCalculate(
            @RequestParam Double averageSalary,
            @RequestParam Integer quantityVacationDays) {

        return vacationPayService.vacationPayCalculating(new VacationPay(averageSalary, quantityVacationDays));
    }

    @GetMapping(value = "/calculate", params = {"averageSalary", "dateStartOfVacation", "dateEndOfVacation"})
    public String VacationPayCalculate(
            @RequestParam Double averageSalary,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateStartOfVacation,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateEndOfVacation) {

        return vacationPayService.vacationPayCalculating(new VacationPay(averageSalary, dateStartOfVacation, dateEndOfVacation));
    }

}
