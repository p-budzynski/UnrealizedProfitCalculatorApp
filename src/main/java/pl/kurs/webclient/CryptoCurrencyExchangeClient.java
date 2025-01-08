package pl.kurs.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CryptoCurrencyExchangeClient {
    private final String CRYPTO_CURRENCY_URL = "https://min-api.cryptocompare.com/data/";
    private final String API_KEY = "36fbb8a981226fedebf563734caea400f65b6ba3ed42966f33bf8277a16ce25c";
    private final RestTemplate restTemplate;

    public String getCurrencyExchangeBtc() {
        return restTemplate.getForObject(CRYPTO_CURRENCY_URL +
                        "price?fsym=BTC&tsyms=USD&api_key={apiKey}",
                String.class, API_KEY);
    }

    public String getCurrencyExchangeBtcHistorical(String data) {
        data = convertDateToTimestampString(data);
        return restTemplate.getForObject(CRYPTO_CURRENCY_URL +
                        "v2/histoday?fsym=BTC&tsym=USD&limit=1&toTs={data}&api_key={apiKey}",
                String.class, data, API_KEY);
    }

    private String convertDateToTimestampString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        long timestamp = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        return String.valueOf(timestamp);
    }

}
