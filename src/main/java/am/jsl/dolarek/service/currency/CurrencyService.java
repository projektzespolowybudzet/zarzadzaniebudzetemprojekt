package am.jsl.dolarek.service.currency;

import am.jsl.dolarek.domain.Currency;
import am.jsl.dolarek.service.BaseService;
import java.util.List;

/**
 * Interfejs usługi, który definiuje wszystkie metody pracy z obiektem domeny {@link Currency}.
 */
public interface CurrencyService extends BaseService<Currency> {
  /**
   * Zwraca walutę według kodu.
   *
   * @return walutę
   */
  Currency getByCode(String isoCode);

  /**
   * Zwraca wszystkie waluty.
   *
   * @return lista walut
   */
  List<Currency> list();

  /**
   * Deletes a currency with the given code.
   *
   * @param code the currency code
   */
  void delete(String code);

  /**
   * Zwraca kurs wymiany dla podanych walut.
   *
   * @param fromCurrency waluta źródłowa
   * @param toCurrency waluta docelowa
   * @return kurs wymiany
   */
  Double getRate(String fromCurrency, String toCurrency);

  /**
   * Czyści pamięć podręczną stawek.
   */
  void clearRatesCache();
}
