package com.app.util;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

/**
 * @author Alvin
 * Enum for comfortable request manipulation and take json string from it
 **/
@Log4j2
@Getter
@RequiredArgsConstructor
public enum RequestType implements Serializable {

    MONO_BANK("https://api.monobank.ua/bank/currency");

    @NonNull
    private String url;

    public String getJson() {
        log.info("Connecting to mono bank...");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(this.url, String.class);
        log.info("Response json from mono bank : \n" + response);
        return response;
    }
}
