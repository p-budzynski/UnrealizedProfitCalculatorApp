package pl.kurs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CryptoHistoricalExchangeDto {
    @JsonProperty("Data")
    private OuterData data;

    @Getter
    @Setter
    public static class OuterData {
        @JsonProperty("Data")
        private List<InnerData> dataList;

        @Getter
        @Setter
        public static class InnerData {
            @JsonProperty("close")
            private double close;
        }
    }

}
