package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.webclient.CryptoCurrencyExchangeClient;

@Service
@RequiredArgsConstructor
public class CryptoCurrencyExchangeService {
    private final CryptoCurrencyExchangeClient client;

    public double getCurrencyExchangeBtc() {
        return client.getCurrencyExchangeBtc();
    }

    public double getCurrencyExchangeBtcHistorical(String data) {
        return client.getCurrencyExchangeBtcHistorical(data);
    }

}
