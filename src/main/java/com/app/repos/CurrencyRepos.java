package com.app.repos;

import com.app.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin
 **/
@Repository
public interface CurrencyRepos extends CrudRepository<Currency, Long> {


}
