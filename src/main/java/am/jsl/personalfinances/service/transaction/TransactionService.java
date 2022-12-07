package am.jsl.personalfinances.service.transaction;

import am.jsl.personalfinances.domain.transaction.Transaction;
import am.jsl.personalfinances.dto.transaction.*;
import am.jsl.personalfinances.search.transaction.TransactionByCategorySearchQuery;
import am.jsl.personalfinances.search.transaction.TransactionSearchQuery;
import am.jsl.personalfinances.service.BaseService;

/**
 * Service interface which defines all the methods for working with {@link Transaction} domain object.
 * @author hamlet
 */
public interface TransactionService extends BaseService<Transaction> {

    /**
     * Retrieves paginated result of transactions for the given search query.
     * @param searchQuery the {@link TransactionSearchQuery} containing query options
     * @return the {@link TransactionSearchResult} containing paged result
     */
    TransactionSearchResult search(TransactionSearchQuery searchQuery);

    /**
     * Creates batch of transactions based on given AddTransactionsDTO object.
     * @param addTransactionsDTO the AddTransactionsDTO
     * @see AddTransactionsDTO
     */
    void createBatch(AddTransactionsDTO addTransactionsDTO);

    /**
     * Returns a transaction details with the given id and user id.
     * @param id the transaction id
     * @param userId the user id
     * @return the {@link TransactionDetailsDTO} containing transaction details
     */
    TransactionDetailsDTO getDetails(long id, long userId);

    /**
     * Retrieves paginated result of transactions for the given search query.
     * @param query the {@link TransactionByCategorySearchQuery} query containing search options
     * @return the list of {@link TransactionByCategoryDTO} items
     */
    TransactionByCategoryResultDTO search(TransactionByCategorySearchQuery query);

    /**
     * Returns transaction amount with the given transaction id and user id
     * @param id the transaction id
     * @param userId the user id
     * @return the amount
     */
    double getAmount(long id, long userId);
}
