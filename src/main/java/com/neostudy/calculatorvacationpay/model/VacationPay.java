package com.neostudy.calculatorvacationpay.model;

import com.neostudy.calculatorvacationpay.model.calendar.CalenderOfWorkingDays;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class VacationPay {

    private Double averageSalary;
    private Integer quantityVacationDays = 0;
    private LocalDate dateStartOfVacation;
    private LocalDate dateEndOfVacation;
    private String mistakes = "";

    public VacationPay(Double averageSalary, Integer quantityVacationDays) {

        if (averageSalaryIsCorrect(averageSalary)) {
            this.averageSalary = averageSalary;
        }

        if (quantityVacationDaysIsCorrect(quantityVacationDays)) {
            this.quantityVacationDays = quantityVacationDays;
        }

    }

    public VacationPay(Double averageSalary, LocalDate DateStartOfVacation, LocalDate DateEndOfVacation) {

        if (averageSalaryIsCorrect(averageSalary)) {
            this.averageSalary = averageSalary;
        }

        if (DatesIsCorrect(DateStartOfVacation, DateEndOfVacation)) {
            this.dateStartOfVacation = DateStartOfVacation;
            this.dateEndOfVacation = DateEndOfVacation;
        }

    }

    private boolean averageSalaryIsCorrect(Double averageSalary) {

        if (averageSalary <= 0) {
            this.mistakes += "Зарплата за 12 месяцев не может быть меньше или равна нулю.";
            return false;
        }

        return true;
    }

    private boolean quantityVacationDaysIsCorrect(Integer quantityVacationDays) {

        if (quantityVacationDays < 1) {
            this.mistakes += "Количество дней отпуска не может быть меньше 1";
            return false;
        }

        if (quantityVacationDays > 56) {
            this.mistakes += "В календарном году работник может взять не более 56 дней отпуска";
            return false;
        }

        return true;
    }

    private boolean DatesIsCorrect(LocalDate dateStartOfVacation, LocalDate dateEndOfVacation) {

        if (!dateStartOfVacation.isBefore(dateEndOfVacation)) {
            this.mistakes += "Дата начала не может быть больше или равнамдаты окончания отпуска.";
            return false;
        }

        Integer quantityVacationDays = CalenderOfWorkingDays.getQuantityVacationDays(dateStartOfVacation, dateEndOfVacation);
        return quantityVacationDaysIsCorrect(quantityVacationDays);
    }

    public boolean IsMistakes() {
        return !mistakes.isEmpty();
    }

}
