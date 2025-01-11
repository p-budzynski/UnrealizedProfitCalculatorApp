package pl.kurs.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.webclient.CurrencyExchangeClient;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {
    private final CurrencyExchangeClient client;

    public double getCurrencyExchange(String userCurrency) {
        JsonNode jsonNode = client.getCurrencyExchange(userCurrency);
        return getValueFromJson(jsonNode, userCurrency);
    }

    public double getCurrencyExchangeHistorical(String userCurrency, String date) {
        JsonNode jsonNode = client.getCurrencyExchangeHistorical(userCurrency, date);
        return getValueFromJson(jsonNode, userCurrency);
    }

    private double getValueFromJson(JsonNode jsonNode, String userCurrency) {
        return jsonNode.path("data").path(userCurrency).path("value").asDouble();
    }
}
