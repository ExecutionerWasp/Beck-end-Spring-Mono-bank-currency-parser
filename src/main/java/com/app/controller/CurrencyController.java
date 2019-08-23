package com.app.controller;

import com.app.domain.Currency;
import com.app.service.CurrencyService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Alvin
 **/
@Log4j2
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    @NonNull
    private final CurrencyService currencyService;

    @GetMapping
    public List<Currency> findAll(){
        var list = currencyService.findAll();

        log.info("Requested a currency collection");

        return list;
    }

    @GetMapping("/mono")
    public String save() {
        log.info("Connecting to mono bank...");
        String url="https://api.monobank.ua/bank/currency";
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(url, String.class);

        JsonParser springParser = JsonParserFactory.getJsonParser();
        List<Object> map = springParser.parseList(resp);

        String mapArray[] = new String[map.size()];
        System.out.println("Items found: " + mapArray.length);

        StringBuilder result = new StringBuilder();
        for (Object entry : map) {
            result
                    .append("\n")
                    .append(entry);
        }
        log.info("Mono bank currency items : " + result);
        log.info("Success");

        return result.toString();
    }
}
