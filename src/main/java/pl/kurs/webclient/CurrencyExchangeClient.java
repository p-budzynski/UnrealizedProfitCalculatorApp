package pl.kurs.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kurs.model.CurrencyExchangeDto;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CurrencyExchangeClient {
    private static final String CURRENCY_URL = "https://api.currencyapi.com/v3/";
    private static final String API_KEY = "cur_live_XvnzwAoaCuYS7ORvfzhf9jDiUFpvADVxVb2E3rkA";
    private final RestTemplate restTemplate;

    public double getCurrencyExchange(String userCurrency) {
        return Objects.requireNonNull(restTemplate.getForObject(CURRENCY_URL +
                                "latest?apikey={apiKey}&currencies={userCurrency}",
                        CurrencyExchangeDto.class, API_KEY, userCurrency))
                .getData().get(userCurrency).getValue();
    }

    public double getCurrencyExchangeHistorical(String userCurrency, String date) {
        return Objects.requireNonNull(restTemplate.getForObject(CURRENCY_URL +
                "historical?apikey={apiKey}&currencies={userCurrency}&date={date}",
                CurrencyExchangeDto.class, API_KEY, userCurrency, date))
                .getData().get(userCurrency).getValue();
    }

}

