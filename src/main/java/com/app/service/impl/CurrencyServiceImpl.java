package com.app.service.impl;

import com.app.domain.Currency;
import com.app.exception.CurrencyIsExistException;
import com.app.exception.CurrencyNotFoundException;
import com.app.exception.MnemonicsNotFoundException;
import com.app.repos.CurrencyRepos;
import com.app.service.CurrencyService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        log.info("Saving currencyA : " + currency);
        return currencyRepos.save(currency);
    }

    @Override
    public Currency save(@NonNull Currency currency, boolean replaceable) {
        if (replaceable) {
            log.info("Saving currencyA...");
            return this.save(currency);
        } else {
            // If currency is not exist -> save it
            if (!this.isExist(currency.getId())) {
                log.info("Currency is exist");
                log.info("Replacing currencyA...");
                return this.save(currency);
            }
        }
        log.info("Currency is exist");
        throw new CurrencyIsExistException("Can not be saved. Replaceable parameter = false");
    }

    @Override
    @Cacheable("currencies")
    public Currency findById(@NonNull Long id) throws CurrencyNotFoundException {
        var currency = currencyRepos.findById(id).orElseThrow(CurrencyNotFoundException::new);
        log.info("Find currencyA by id : " + id);
        return currency;
    }

    @Override
    @Cacheable("currencies")
    public Currency findByMnemonics(@NonNull String mnemonics) {
        log.info("Searching by mnemonics : " + mnemonics);
        var val = currencyRepos.findByMnemonics(mnemonics);
        if (Objects.isNull(val)) {
            throw new MnemonicsNotFoundException();
        }
        return val;
    }

    @Override
    @Cacheable("currencies")
    public List<Currency> findAll() {
        return currencyRepos.findAll();
    }

    @Override
    public void delete(Currency currency) {
        log.info("Removing currencyA : " + currency);
        currencyRepos.delete(currency);
    }

    @Override
    public boolean isExist(Long id) {
        log.info("Checking currencyA on existing with id : " + id);
        boolean val = currencyRepos.existsById(id);
        log.info("Currency existion status : " + val);
        return val;
    }
}
