package am.jsl.personalfinances.dao.account;


import am.jsl.personalfinances.dao.BaseDao;
import am.jsl.personalfinances.domain.account.Account;
import am.jsl.personalfinances.dto.account.AccountListDTO;
import am.jsl.personalfinances.dto.account.AdjustBalanceDTO;

import java.util.List;

/**
 * Interfejs Dao do uzyskiwania dostępu do obiektu domeny {@link Account}.
 */
public interface AccountDao extends BaseDao<Account> {

/**
*Zwraca wszystkie konta dla danego użytkownika.
*@param userId identyfikator użytkownika
*@return listę rachunków
*/
    List<AccountListDTO> getAccounts(long userId);

/**
*Zwraca aktywne konta dla danego użytkownika.
*@param userId identyfikator użytkownika powiązany z kontem
*@return listę aktywnych kont
*/
    List<AccountListDTO> getActiveAccounts(long userId);

/**
*Aktualizuje stan konta na podstawie podanego obiektu AdjustBalanceDTO.
*@param AdjustBalance obiektu AdjustBalanceDTO
*@zobacz AdjustBalanceDTO
*/
    void updateBalance(AdjustBalanceDTO adjustBalance);

/**
*Zmniejsza saldo danego konta.
*@param id identyfikator konta
*@param userId identyfikator użytkownika powiązany z kontem
*@param kwota kwota do odjęcia od salda
*/
    void decreaseBalance(long id, long userId, double amount);

/**
*Zwiększa saldo danego konta.
*@param id identyfikator konta
*@param userId identyfikator użytkownika powiązany z kontem
*@param kwota kwota do dodania do salda
*/
    void increaseBalance(long id, long userId, double amount);
}
