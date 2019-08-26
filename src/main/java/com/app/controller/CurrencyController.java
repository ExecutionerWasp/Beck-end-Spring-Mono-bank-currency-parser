package com.app.controller;

import com.app.domain.Currency;
import com.app.service.CurrencyService;
import com.app.util.RequestType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Alvin
 **/
@Log4j2
@RestController
@RequestMapping(value = "/currency", produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class CurrencyController {

    @NonNull
    private final CurrencyService currencyService;

    /**
     * @return all currency from database
     * */
    @GetMapping
    public List<Currency> findAll() {
        var list = currencyService.findAll();
        log.info("Requested a currencyA collection");
        return list;
    }

    /**
     * @return Monitoring json all courses from mono bank
     * */
    @GetMapping("/mono")
    public String getDataFromMonoBank() {
        return RequestType.MONO_BANK.getJson();
    }

    /**
     * Saving currency request
     * @param code - currency code
     * @param mnemonics - currency mnemonics
     * @param description - currency description
     * @param replaceable - flag for replace a existable currency
     * @return saved currency
     * */
    @GetMapping("/save")
    public Currency saveCurrency(
            @RequestParam(name = "c") String code,
            @RequestParam(name = "m") String mnemonics,
            @RequestParam(name = "d", required = false) String description,
            @RequestParam(name="replace", required = false, defaultValue = "false") Boolean replaceable
            ) {
        var currency = Currency.builder()
                .id(Long.valueOf(code))
                .mnemonics(mnemonics.toUpperCase())
                .description(description)
                .build();
        log.info("Currency has been taken");
        var currencyFromDb = currencyService.save(currency, replaceable);
        log.info("Currency : " + currencyFromDb + " has been saved");
        return currencyFromDb;
    }
}
