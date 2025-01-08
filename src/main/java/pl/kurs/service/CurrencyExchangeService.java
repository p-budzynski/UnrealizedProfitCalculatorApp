package pl.kurs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.webclient.CurrencyExchangeClient;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {
    private final CurrencyExchangeClient client;
    private final ObjectMapper objectMapper;

    public double getCurrencyExchange(String userCurrency) throws JsonProcessingException {
        String jsonString = client.getCurrencyExchange(userCurrency);
        return getValueFromJson(jsonString, userCurrency);
    }

    public double getCurrencyExchangeHistorical(String userCurrency, String date) throws JsonProcessingException {
        String jsonString = client.getCurrencyExchangeHistorical(userCurrency, date);
        return getValueFromJson(jsonString, userCurrency);
    }

    private double getValueFromJson(String jsonString, String userCurrency) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(jsonString);
        return rootNode.path("data").path(userCurrency).path("value").asDouble();
    }
}
