package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.webclient.CurrencyExchangeClient;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {
    private final CurrencyExchangeClient client;

    public double getCurrencyExchange(String userCurrency) {
        return client.getCurrencyExchange(userCurrency);
    }

    public double getCurrencyExchangeHistorical(String userCurrency, String date) {
        return client.getCurrencyExchangeHistorical(userCurrency, date);
    }

}
