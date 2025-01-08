package pl.kurs.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BrokerConfig {

    @Value("${brokerBuyCommission}")
    private double buyCommission;

    @Value("${brokerSellCommission}")
    private double sellCommission;

}
