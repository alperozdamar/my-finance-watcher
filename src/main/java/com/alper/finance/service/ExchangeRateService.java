package com.alper.finance.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {

    public String getUsdToTryRate() {
        try {
            Document doc = Jsoup.connect("https://www.doviz.com").get();
            Element usdElement = doc.selectFirst("span[data-socket-key='USD']");
            if (usdElement != null) {
                return usdElement.text();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }
}
