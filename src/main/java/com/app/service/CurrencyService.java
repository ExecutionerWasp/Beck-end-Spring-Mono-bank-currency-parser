package com.app.service;

import com.app.domain.Currency;
import lombok.NonNull;

import java.util.List;

/**
 * @author Alvin
 **/

public interface CurrencyService {

    Currency save(@NonNull Currency currency);

    List<Currency> findAll();

    void delete(@NonNull Currency currency);

    boolean isExist(@NonNull Long id);

    default boolean isExist(@NonNull Currency currency) {
        return this.isExist(currency.getId());
    }
}
