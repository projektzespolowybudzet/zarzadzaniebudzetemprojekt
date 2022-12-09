package am.jsl.personalfinances.dao.transaction;

import am.jsl.personalfinances.dao.BaseDao;
import am.jsl.personalfinances.domain.transaction.Transaction;
import am.jsl.personalfinances.dto.transaction.TransactionByCategoryDTO;
import am.jsl.personalfinances.dto.transaction.TransactionDetailsDTO;
import am.jsl.personalfinances.dto.transaction.TransactionSearchResult;
import am.jsl.personalfinances.search.transaction.TransactionByCategorySearchQuery;
import am.jsl.personalfinances.search.transaction.TransactionSearchQuery;

import java.util.List;

/**
*Interfejs Dao do uzyskiwania dostępu do obiektu domeny {@link Transaction}.
*/
public interface TransactionDao extends BaseDao<Transaction> {
/**
*Pobiera wyniki transakcji z podziałem na strony dla danego zapytania wyszukiwania.
*@param searchQuery {@link TransactionSearchQuery} zawierający opcje zapytania
*@zwróć {@link TransactionSearchResult} zawierający wynik stronicowania
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
*@zwróć {@link TransactionDetailsDTO} zawierający szczegóły transakcji
*/
    TransactionDetailsDTO getDetails(long id, long userId);

/**
*Pobiera wyniki transakcji z podziałem na strony dla danego zapytania wyszukiwania.
*@param zapytanie o zapytanie {@link TransactionByCategorySearchQuery} zawierające opcje wyszukiwania
*@zwróć listę pozycji {@link TransactionByCategoryDTO}
*/
    List<TransactionByCategoryDTO> search(TransactionByCategorySearchQuery query);

/**
*Zwraca kwotę transakcji z podanym identyfikatorem transakcji i identyfikatorem użytkownika
*@param id identyfikator transakcji
*@param userId identyfikator użytkownika
*@zwrot kwoty
*/
    double getAmount(long id, long userId);
}
