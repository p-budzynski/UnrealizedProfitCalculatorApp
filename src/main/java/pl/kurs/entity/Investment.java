package pl.kurs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "investments")
@NoArgsConstructor
@Getter
@Setter
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_currency", nullable = false, length = 10)
    private String userCurrency;

    @Column(name = "investment_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal investmentAmount;

    @Column(name = "amount_after_investment", precision = 15, scale = 2)
    private BigDecimal amountAfterInvestment;

    @Column(name = "difference_after_investment", precision = 15, scale = 2)
    private BigDecimal differenceAfterInvestment;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column(name = "initial_usd_rate", nullable = false, precision = 15, scale = 2)
    private BigDecimal initialUsdRate;

    @Column(name = "initial_btc_rate", nullable = false, precision = 15, scale = 2)
    private BigDecimal initialBtcRate;

    @Column(name = "final_usd_rate", nullable = false, precision = 15, scale = 2)
    private BigDecimal finalUsdRate;

    @Column(name = "final_btc_rate", nullable = false, precision = 15, scale = 2)
    private BigDecimal finalBtcRate;

    public Investment(String userCurrency, BigDecimal investmentAmount, BigDecimal amountAfterInvestment, BigDecimal differenceAfterInvestment, String startDate, String endDate, BigDecimal initialUsdRate, BigDecimal initialBtcRate, BigDecimal finalUsdRate, BigDecimal finalBtcRate) {
        this.userCurrency = userCurrency;
        this.investmentAmount = investmentAmount;
        this.amountAfterInvestment = amountAfterInvestment;
        this.differenceAfterInvestment = differenceAfterInvestment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initialUsdRate = initialUsdRate;
        this.initialBtcRate = initialBtcRate;
        this.finalUsdRate = finalUsdRate;
        this.finalBtcRate = finalBtcRate;
    }

    @Override
    public String toString() {
        return "Investment: " + investmentAmount + " " + userCurrency +
                "\nStart date: " + startDate + ", USD rate: " + initialUsdRate + " " + userCurrency +
                ", BTC rate: " + initialBtcRate + " USD\n" +
                "End date: " + endDate + ", USD rate: " + finalUsdRate + " " + userCurrency +
                ", BTC rate: " + finalBtcRate + " USD\n" +
                "Amount after investment: " + amountAfterInvestment +
                ", Difference after investment: " + differenceAfterInvestment + "\n";
    }
}
