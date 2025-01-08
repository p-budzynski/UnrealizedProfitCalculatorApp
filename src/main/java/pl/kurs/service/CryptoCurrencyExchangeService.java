package pl.kurs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.webclient.CryptoCurrencyExchangeClient;

@Service
@RequiredArgsConstructor
public class CryptoCurrencyExchangeService {
    private final CryptoCurrencyExchangeClient client;
    private final ObjectMapper objectMapper;

    public double getCurrencyExchangeBtc() throws JsonProcessingException {
        String jsonString = client.getCurrencyExchangeBtc();
        return getValueBtcFromJson(jsonString);
    }

    public double getCurrencyExchangeBtcHistorical(String data) throws JsonProcessingException {
        String jsonString = client.getCurrencyExchangeBtcHistorical(data);
        return getHistoricalValueBtcFromJson(jsonString);
    }

    private double getValueBtcFromJson(String jsonString) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(jsonString);
        return rootNode.path("USD").asDouble();
    }

    private double getHistoricalValueBtcFromJson(String jsonString) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(jsonString);
        return rootNode.path("Data").path("Data").get(1).path("close").asDouble();
    }
}
