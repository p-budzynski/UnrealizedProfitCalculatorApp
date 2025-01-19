package pl.kurs.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kurs.model.CryptoExchangeDto;
import pl.kurs.model.CryptoHistoricalExchangeDto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CryptoCurrencyExchangeClient {
    private final String CRYPTO_CURRENCY_URL = "https://min-api.cryptocompare.com/data/";
    private final String API_KEY = "36fbb8a981226fedebf563734caea400f65b6ba3ed42966f33bf8277a16ce25c";
    private final RestTemplate restTemplate;

    public double getCurrencyExchangeBtc() {
        return Objects.requireNonNull(restTemplate.getForObject(CRYPTO_CURRENCY_URL +
                        "price?fsym=BTC&tsyms=USD&api_key={apiKey}",
                CryptoExchangeDto.class, API_KEY))
                .getCryptoValue();
    }

    public double getCurrencyExchangeBtcHistorical(String date) {
        date = convertDateToTimestampString(date);
        return Objects.requireNonNull(restTemplate.getForObject(CRYPTO_CURRENCY_URL +
                        "v2/histoday?fsym=BTC&tsym=USD&limit=1&toTs={data}&api_key={apiKey}",
                CryptoHistoricalExchangeDto.class, date, API_KEY))
                .getData().getDataList().get(1).getClose();
    }

    private String convertDateToTimestampString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        long timestamp = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        return String.valueOf(timestamp);
    }

}
