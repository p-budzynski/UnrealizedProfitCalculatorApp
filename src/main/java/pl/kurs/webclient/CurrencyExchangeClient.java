package pl.kurs.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CurrencyExchangeClient {
    private static final String CURRENCY_URL = "https://api.currencyapi.com/v3/";
    private static final String API_KEY = "cur_live_XvnzwAoaCuYS7ORvfzhf9jDiUFpvADVxVb2E3rkA";
    private final RestTemplate restTemplate;

    public String getCurrencyExchange(String userCurrency) {
        return restTemplate.getForObject(CURRENCY_URL +
                "latest?apikey={apiKey}&currencies={userCurrency}",
                String.class, API_KEY, userCurrency);
    }

    public String getCurrencyExchangeHistorical(String userCurrency, String date) {
        return restTemplate.getForObject(CURRENCY_URL +
                "historical?apikey={apiKey}&currencies={userCurrency}&date={date}",
                String.class, API_KEY, userCurrency, date);
    }

}

