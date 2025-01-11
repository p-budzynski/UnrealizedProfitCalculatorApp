package pl.kurs.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.webclient.CryptoCurrencyExchangeClient;

@Service
@RequiredArgsConstructor
public class CryptoCurrencyExchangeService {
    private final CryptoCurrencyExchangeClient client;

    public double getCurrencyExchangeBtc() {
        JsonNode jsonNode = client.getCurrencyExchangeBtc();
        return getValueBtcFromJson(jsonNode);
    }

    public double getCurrencyExchangeBtcHistorical(String data) {
        JsonNode jsonNode = client.getCurrencyExchangeBtcHistorical(data);
        return getHistoricalValueBtcFromJson(jsonNode);
    }

    private double getValueBtcFromJson(JsonNode jsonNode) {
        return jsonNode.path("USD").asDouble();
    }

    private double getHistoricalValueBtcFromJson(JsonNode jsonNode) {
        return jsonNode.path("Data").path("Data").get(1).path("close").asDouble();
    }
}
