package pl.kurs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.kurs.entity.Investment;
import pl.kurs.exception.InvestmentNotFoundException;
import pl.kurs.service.InvestmentService;
import pl.kurs.service.UserInputService;

import java.io.IOException;

@ComponentScan("pl.kurs")
public class Application {
    public static void main(String[] args) throws IOException, InvestmentNotFoundException {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);

        UserInputService userInputService = ctx.getBean(UserInputService.class);
        InvestmentService investmentService = ctx.getBean(InvestmentService.class);

        String currency = userInputService.readCurrency();
        double amount = userInputService.readAmount();
        String startDate = userInputService.readStartDate();
        String endDate = userInputService.readEndDate();

        Investment investment = investmentService.calculateProfit(currency, amount, startDate, endDate);

        System.out.println("Profit: " + investment.getDifferenceAfterInvestment() + " " + currency);

        System.out.println(investmentService.get(2L));


    }
}
