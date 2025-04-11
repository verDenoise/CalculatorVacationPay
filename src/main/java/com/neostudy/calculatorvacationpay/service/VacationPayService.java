package com.neostudy.calculatorvacationpay.service;

import com.neostudy.calculatorvacationpay.model.calendar.CalenderOfWorkingDays;
import com.neostudy.calculatorvacationpay.model.VacationPay;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class VacationPayService {

    public static final String FORMAT_TWO_DECIMAL = "#.##";

    public String vacationPayCalculating() {

        return "Для расчета отпускных необходимо ввести в командную строку запрос:" +
                "<br>http://localhost:8080/calculate?averageSalary='Зарплата за 12 месяцев'&quantityVacationDays='Количесвто дней отпуска'" +
                "<br>или" +
                "<br>http://localhost:8080/calculate?averageSalary='Зарплата за 12 месяцев'&dateStartOfVacation='Дата начала отпуска'&dateEndOfVacation='Дата окончания отпуска'" +
                "<br>дата в формате 'yyyy-MM-dd'";
    }

    public String vacationPayCalculating(VacationPay vacationPay) {

        if (vacationPay.IsMistakes()) {
            return vacationPay.getMistakes();
        }

        Integer quantityVacationDays;
        if (vacationPay.getQuantityVacationDays() != 0) {
            quantityVacationDays = vacationPay.getQuantityVacationDays();
        } else {
            quantityVacationDays = CalenderOfWorkingDays.getQuantityVacationDays(vacationPay.getDateStartOfVacation(), vacationPay.getDateEndOfVacation());
        }

        Double vacationMoney = vacationPay.getAverageSalary() * quantityVacationDays / CalenderOfWorkingDays.AVERAGE_MONTHLY_NUMBER_OF_DAYS;
        return new DecimalFormat(FORMAT_TWO_DECIMAL).format(vacationMoney);
    }

}
