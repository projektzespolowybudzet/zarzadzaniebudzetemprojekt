package am.jsl.personalfinances.service.transaction;

import am.jsl.personalfinances.domain.transaction.Transaction;
import am.jsl.personalfinances.dto.transaction.*;
import am.jsl.personalfinances.search.transaction.TransactionByCategorySearchQuery;
import am.jsl.personalfinances.search.transaction.TransactionSearchQuery;
import am.jsl.personalfinances.service.BaseService;

/**
 *Interfejs usługi, który definiuje wszystkie metody pracy z obiektem domeny {@link Transaction}.
 */
public interface TransactionService extends BaseService<Transaction> {

    /**
     *Pobiera wyniki transakcji z podziałem na strony dla danego zapytania wyszukiwania.
     *@param searchQuery {@link TransactionSearchQuery} zawierający opcje zapytania
     *return {@link TransactionSearchResult} zawierający wynik stronicowania
     */
    TransactionSearchResult search(TransactionSearchQuery searchQuery);

    /**
     *Tworzy partię transakcji na podstawie podanego obiektu AddTransactionsDTO.
     *@param addTransactionsDTO AddTransactionsDTO
     *@see AddTransactionsDTO
     */
    void createBatch(AddTransactionsDTO addTransactionsDTO);

    /**
     *Zwraca szczegóły transakcji z podanym identyfikatorem i identyfikatorem użytkownika.
     *@param id identyfikator transakcji
     *@param userId identyfikator użytkownika
     *return {@link TransactionDetailsDTO} zawierający szczegóły transakcji
     */
    TransactionDetailsDTO getDetails(long id, long userId);

    /**
     *Pobiera wyniki transakcji z podziałem na strony dla danego zapytania wyszukiwania.
     *@param query o zapytanie {@link TransactionByCategorySearchQuery} zawierające opcje wyszukiwania
     *return listę pozycji {@link TransactionByCategoryDTO}
     */
    TransactionByCategoryResultDTO search(TransactionByCategorySearchQuery query);

    /**
     *Zwraca kwotę transakcji z podanym identyfikatorem transakcji i identyfikatorem użytkownika
     *@param id identyfikator transakcji
     *@param userId identyfikator użytkownika
     *@return kwota
     */
    double getAmount(long id, long userId);
}
