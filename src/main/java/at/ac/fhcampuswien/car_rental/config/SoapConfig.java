package at.ac.fhcampuswien.car_rental.config;

import at.ac.fhcampuswien.car_rental.soap.client.CurrencyConversionService;
import at.ac.fhcampuswien.car_rental.soap.client.CurrencyConversionService_Service;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.handler.Handler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Log4j2
public class SoapConfig {
    @Value("${currency-converter.wsdl}")
    String currencyConverterWsdlUrl;
    @Bean
    public CurrencyConversionService createCurrencyConverterService() {
        try {
            CurrencyConversionService_Service currencyConversionServiceService = new CurrencyConversionService_Service(new URL(this.currencyConverterWsdlUrl));
            currencyConversionServiceService.setHandlerResolver(portInfo -> {
                List<Handler> handlerList = new ArrayList<>();
                handlerList.add(new SoapAuthHandler());
                return handlerList;
            });
            return currencyConversionServiceService.getCurrencyConversionService();
        } catch (MalformedURLException e) {
            log.error("Could not access the wsdl file");
            throw new RuntimeException(e);
        }
    }
}
