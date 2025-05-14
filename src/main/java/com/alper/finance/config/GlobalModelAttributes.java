package com.alper.finance.config;

import com.alper.finance.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @Value("${app.version:unknown}")
    private String appVersion;

    private final ExchangeRateService exchangeRateService;

    public GlobalModelAttributes(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @ModelAttribute("appVersion")
    public String appVersion() {
        return appVersion;
    }

    @ModelAttribute("usdToTryRate")
    public String usdToTryRate() {
        return exchangeRateService.getUsdToTryRate();
    }
}
