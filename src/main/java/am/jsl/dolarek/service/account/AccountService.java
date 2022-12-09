package am.jsl.dolarek.service.account;

import java.util.List;

import am.jsl.dolarek.domain.account.Account;
import am.jsl.dolarek.dto.account.AccountListDTO;
import am.jsl.dolarek.dto.account.AdjustBalanceDTO;
import am.jsl.dolarek.service.BaseService;

/**
*Interfejs usługi, który definiuje wszystkie metody pracy z obiektem domeny {@link Account}.
*/
public interface AccountService extends BaseService<Account> {
/**
*Zwraca wszystkie konta dla danego użytkownika.
*@param userId identyfikator użytkownika
*@return listę kont
*/
    List<AccountListDTO> getAccounts(long userId);

/**
*Aktualizuje stan konta na podstawie podanego obiektu AdjustBalanceDTO.
*@param AdjustBalance obiektu AdjustBalanceDTO
*@see AdjustBalanceDTO
*/
    void updateBalance(AdjustBalanceDTO adjustBalance);

/**
*Zwraca aktywne konta dla danego użytkownika.
*@param userId identyfikator użytkownika
*@return listę aktywnych kont
*/
    List<AccountListDTO> getActiveAccounts(long userId);
}
