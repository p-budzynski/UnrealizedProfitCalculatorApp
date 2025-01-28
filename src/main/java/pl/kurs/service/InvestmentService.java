package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pl.kurs.config.BrokerConfig;
import pl.kurs.dao.InvestmentDao;
import pl.kurs.entity.Investment;
import pl.kurs.exception.InvestmentNotFoundException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class InvestmentService {
    private final CurrencyExchangeService currencyExchangeService;
    private final CryptoCurrencyExchangeService cryptoCurrencyExchangeService;
    private final BrokerConfig brokerConfig;
    private final InvestmentDao investmentDao;

    public Investment calculateProfit(String currency, double amount, String startDate, String endDate) throws IOException {
        double purchaseRate = getPurchaseRate(currency, startDate);
        double sellRate = getSellRate(currency, endDate);

        double btcStartPrice = cryptoCurrencyExchangeService.getCurrencyExchangeBtcHistorical(startDate);
        double btcEndPrice = (endDate == null) ? cryptoCurrencyExchangeService.getCurrencyExchangeBtc() :
                cryptoCurrencyExchangeService.getCurrencyExchangeBtcHistorical(endDate);

        BigDecimal usdInvested = calculateUsdInvested(amount, purchaseRate, currency);

        BigDecimal btcBought = usdInvested.divide(BigDecimal.valueOf(btcStartPrice), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(brokerConfig.getBuyCommission()));

        BigDecimal usdGained = btcBought.multiply(BigDecimal.valueOf(btcEndPrice))
                .multiply(BigDecimal.valueOf(brokerConfig.getSellCommission()));

        if (!currency.equalsIgnoreCase("USD")) {
            usdGained = usdGained.multiply(BigDecimal.valueOf(sellRate)
                    .multiply(BigDecimal.valueOf(brokerConfig.getSellCommission())));
        }

        BigDecimal profit = usdGained.subtract(BigDecimal.valueOf(amount));

        Investment investment = createInvestment(currency, amount, startDate, endDate, purchaseRate, sellRate,
                btcStartPrice, btcEndPrice, usdGained, profit);

        investmentDao.save(investment);

        return investment;
    }

    public Investment get(Long id) throws InvestmentNotFoundException, IOException {
        return investmentDao.get(id);
    }

    private double getPurchaseRate(String currency, String startDate) {
        if (currency.equalsIgnoreCase("USD")) {
            return 1;
        }
        return currencyExchangeService.getCurrencyExchangeHistorical(currency, startDate);
    }

    private double getSellRate(String currency, String endDate) {
        if (currency.equalsIgnoreCase("USD")) {
            return 1;
        }
        return (endDate == null) ? currencyExchangeService.getCurrencyExchange(currency) :
                currencyExchangeService.getCurrencyExchangeHistorical(currency, endDate);
    }

    private BigDecimal calculateUsdInvested(double amount, double purchaseRate, String currency) {
        BigDecimal usdInvested = BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(purchaseRate), RoundingMode.HALF_UP);
        if (!currency.equalsIgnoreCase("USD")) {
            usdInvested = usdInvested.multiply(BigDecimal.valueOf(brokerConfig.getBuyCommission()));
        }
        return usdInvested;
    }

    private Investment createInvestment(String currency, double amount, String startDate, String endDate,
                                        double purchaseRate, double sellRate, double btcStartPrice,
                                        double btcEndPrice, BigDecimal usdGained, BigDecimal profit) {
        Investment investment = new Investment();
        investment.setUserCurrency(currency);
        investment.setInvestmentAmount(BigDecimal.valueOf(amount));
        investment.setAmountAfterInvestment(usdGained);
        investment.setStartDate(startDate);
        investment.setEndDate((endDate == null) ? LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : endDate);
        investment.setInitialUsdRate(BigDecimal.valueOf(purchaseRate).setScale(2, RoundingMode.HALF_UP));
        investment.setInitialBtcRate(BigDecimal.valueOf(btcStartPrice).setScale(2, RoundingMode.HALF_UP));
        investment.setFinalUsdRate(BigDecimal.valueOf(sellRate).setScale(2, RoundingMode.HALF_UP));
        investment.setFinalBtcRate(BigDecimal.valueOf(btcEndPrice).setScale(2, RoundingMode.HALF_UP));
        investment.setDifferenceAfterInvestment(profit.setScale(2, RoundingMode.HALF_UP));
        return investment;
    }

}

