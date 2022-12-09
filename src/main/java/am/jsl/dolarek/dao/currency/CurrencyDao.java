package am.jsl.dolarek.dao.currency;

import am.jsl.dolarek.dao.BaseDao;
import am.jsl.dolarek.domain.Currency;

import java.util.List;

/**
*Interfejs Dao do uzyskiwania dostępu do obiektu domeny {@link Currency}.
*/
public interface CurrencyDao extends BaseDao<Currency> {
/**
*Zwraca walutę według kodu.
*@return Currency
*/
    Currency getByCode(String isoCode);

/**
*Zwraca wszystkie waluty.
*@return lista Currency
*/
    List<Currency> list();

/**
*Usuwa walutę z podanym kodem.
*@param code kod waluty
*/
    void delete(String code);
}
