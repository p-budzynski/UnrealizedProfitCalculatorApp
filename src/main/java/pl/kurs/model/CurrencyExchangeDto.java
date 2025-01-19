package pl.kurs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyExchangeDto {
    @JsonProperty("data")
    private Map<String, DataDto> data;

    @Getter
    @Setter
    public static class DataDto {
        @JsonProperty("code")
        private String code;
        @JsonProperty("value")
        private double value;
    }
}
