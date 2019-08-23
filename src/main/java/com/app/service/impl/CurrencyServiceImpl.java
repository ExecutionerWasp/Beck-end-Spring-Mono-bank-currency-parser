package com.app.service.impl;

import com.app.domain.Currency;
import com.app.repos.CurrencyRepos;
import com.app.service.CurrencyService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alvin
 **/
@Log4j2
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    @NonNull
    private final CurrencyRepos currencyRepos;

    @Override
    public Currency save(Currency currency) {
        log.info("Saving currency : " + currency);
        return currencyRepos.save(currency);
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepos.findAll();
    }

    @Override
    public void delete(Currency currency) {
        log.info("Removing currency : " + currency);
        currencyRepos.delete(currency);
    }

    @Override
    public boolean isExist(Long id) {
        log.info("Checking currency on existing with id : " + id);
        return currencyRepos.existsById(id);
    }
}
