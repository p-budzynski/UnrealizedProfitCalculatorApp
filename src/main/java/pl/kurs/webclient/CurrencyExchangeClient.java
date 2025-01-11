package pl.kurs.webclient;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CurrencyExchangeClient {
    private static final String CURRENCY_URL = "https://api.currencyapi.com/v3/";
    private static final String API_KEY = "cur_live_XvnzwAoaCuYS7ORvfzhf9jDiUFpvADVxVb2E3rkA";
    private final RestTemplate restTemplate;

    public JsonNode getCurrencyExchange(String userCurrency) {
        return restTemplate.getForObject(CURRENCY_URL +
                "latest?apikey={apiKey}&currencies={userCurrency}",
                JsonNode.class, API_KEY, userCurrency);
    }

    public JsonNode getCurrencyExchangeHistorical(String userCurrency, String date) {
        return restTemplate.getForObject(CURRENCY_URL +
                "historical?apikey={apiKey}&currencies={userCurrency}&date={date}",
                JsonNode.class, API_KEY, userCurrency, date);
    }

}

