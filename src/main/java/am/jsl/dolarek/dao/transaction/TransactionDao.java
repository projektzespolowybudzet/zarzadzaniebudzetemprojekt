package am.jsl.dolarek.dao.transaction;

import java.util.List;

import am.jsl.dolarek.dao.BaseDao;
import am.jsl.dolarek.domain.transaction.Transaction;
import am.jsl.dolarek.dto.transaction.TransactionByCategoryDTO;
import am.jsl.dolarek.dto.transaction.TransactionDetailsDTO;
import am.jsl.dolarek.dto.transaction.TransactionSearchResult;
import am.jsl.dolarek.search.transaction.TransactionByCategorySearchQuery;
import am.jsl.dolarek.search.transaction.TransactionSearchQuery;

/**
*Interfejs Dao do uzyskiwania dostępu do obiektu domeny {@link Transaction}.
*/
public interface TransactionDao extends BaseDao<Transaction> {
/**
*Pobiera wyniki transakcji z podziałem na strony dla danego zapytania wyszukiwania.
*@param searchQuery {@link TransactionSearchQuery} zawierający opcje zapytania
*@return {@link TransactionSearchResult} zawierający wynik stronicowania
*/
    TransactionSearchResult search(TransactionSearchQuery searchQuery);

/**
*Tworzy zadaną listę transakcji.
*Transakcje @param lista transakcji
*/
    void createBatch(List<Transaction> transactions);

/**
*Zwraca szczegóły transakcji z podanym identyfikatorem i identyfikatorem użytkownika.
*@param id identyfikator transakcji
*@param userId identyfikator użytkownika
*@return {@link TransactionDetailsDTO} zawierający szczegóły transakcji
*/
    TransactionDetailsDTO getDetails(long id, long userId);

/**
*Pobiera wyniki transakcji z podziałem na strony dla danego zapytania wyszukiwania.
*@param zapytanie o zapytanie {@link TransactionByCategorySearchQuery} zawierające opcje wyszukiwania
*@return listę pozycji {@link TransactionByCategoryDTO}
*/
    List<TransactionByCategoryDTO> search(TransactionByCategorySearchQuery query);

/**
*Zwraca kwotę transakcji z podanym identyfikatorem transakcji i identyfikatorem użytkownika
*@param id identyfikator transakcji
*@param userId identyfikator użytkownika
*return kwota
*/
    double getAmount(long id, long userId);
}
