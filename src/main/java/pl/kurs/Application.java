package pl.kurs;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.kurs.entity.Investment;
import pl.kurs.service.InvestmentService;
import pl.kurs.service.UserInputService;

@ComponentScan("pl.kurs")
public class Application {
    public static void main(String[] args) throws JsonProcessingException {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);

        UserInputService userInputService = ctx.getBean(UserInputService.class);
        InvestmentService investmentService = ctx.getBean(InvestmentService.class);

        String currency = userInputService.readCurrency();
        double amount = userInputService.readAmount();
        String startDate = userInputService.readStartDate();
        String endDate = userInputService.readEndDate();

        Investment investment = investmentService.calculateProfit(currency, amount, startDate, endDate);

        System.out.println("Profit: " + investment.getDifferenceAfterInvestment() + " " + currency);

    }
}
