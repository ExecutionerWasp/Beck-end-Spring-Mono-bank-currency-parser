package com.app.repos;

import com.app.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin
 **/
@Repository
public interface CurrencyRepos extends JpaRepository<Currency, Long> {


}
