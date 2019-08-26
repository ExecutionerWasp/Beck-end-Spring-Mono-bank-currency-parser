package com.app.service;

import com.app.domain.Currency;
import com.app.domain.Mnemonics;
import com.app.exception.CurrencyNotFoundException;
import lombok.NonNull;

import java.util.List;

/**
 * @author Alvin
 **/

public interface CurrencyService {

    Currency save(@NonNull Currency currency);

    Currency save(@NonNull Currency currency, boolean replaceable);

    Currency findById(@NonNull Long id) throws CurrencyNotFoundException;

    default Currency findById(@NonNull String id) throws CurrencyNotFoundException {
        return this.findById(Long.valueOf(id));
    }

    Currency findByMnemonics(@NonNull String mnemonics);

    default Currency findByMnemonics(@NonNull Mnemonics mnemonics) {
        return this.findByMnemonics(mnemonics.getMnemonic());
    }

    List<Currency> findAll();

    void delete(@NonNull Currency currency);

    boolean isExist(@NonNull Long id);

    default boolean isExist(@NonNull Currency currency) {
        return this.isExist(currency.getId());
    }
}
