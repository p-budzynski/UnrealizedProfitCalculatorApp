package pl.kurs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoExchangeDto {
    @JsonProperty("USD")
    private double cryptoValue;
}
