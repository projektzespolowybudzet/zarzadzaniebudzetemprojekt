package am.jsl.dolarek.web.controller.account;

import am.jsl.dolarek.domain.account.Account;
import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.dto.account.AccountDTO;
import am.jsl.dolarek.dto.account.AccountListDTO;
import am.jsl.dolarek.dto.account.AdjustBalanceDTO;
import am.jsl.dolarek.ex.CannotDeleteException;
import am.jsl.dolarek.service.account.AccountService;
import am.jsl.dolarek.service.currency.CurrencyService;
import am.jsl.dolarek.web.controller.BaseController;
import am.jsl.dolarek.web.util.I18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static am.jsl.dolarek.web.util.WebUtils.*;

/**
 * AccountController definiuje metody funkcjonalności stron konta
 * takie jak wyszukiwanie, widok, dodaj, edytuj konta.
 */
@Controller
@RequestMapping(value = "/account")
@Lazy
public class AccountController extends BaseController {
    /**
     * Ścieżki szablonów konta
     */
    public static final String REDIRECT_ACCOUNT_LIST = "redirect:list";
    public static final String FORWARD_ACCOUNT_LIST = "account/account-list";
    public static final String FORWARD_ACCOUNT_EDIT = "account/account-edit";
    public static final String FORWARD_ACCOUNT_ADD = "account/account-add";
    public static final String FORWARD_ACCOUNT_LOOKUP_LIST = "account/account-lookup-result :: accountLookupList";

    @Autowired
    private transient AccountService accountService;
    @Autowired
    private transient CurrencyService currencyService;
    /**
     * Wywołane, gdy użytkownik otwiera listę konta.
     * @param model Model
     * @return strona z szablonu FORWARD_ACCOUNT_LIST
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String list(Model model) {
        User user = getUser();
        List<AccountListDTO> accounts = accountService.getAccounts(user.getId());
        model.addAttribute(ACCOUNTS, accounts);
        return FORWARD_ACCOUNT_LIST;
    }

    /**
     * Wywołane metodami AJAX do pobierania opcji wybierania konta.
     * @param model Model
     * @return strona z szablonu FORWARD_ACCOUNT_LOOKUP_LIST
     */
    @RequestMapping(value = {"/lookup"}, method = RequestMethod.GET)
    public String lookup(Model model) {
        User user = getUser();
        model.addAttribute(ACCOUNTS, accountService.lookup(user.getId()));
        return FORWARD_ACCOUNT_LOOKUP_LIST;
    }

    /**
     * Wywołane, gdy użytkownik kliknie na link z strony listy kont.
     *
     * @param model Model
     * @return strona z szablonu FORWARD_ACCOUNT_ADD
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        if (!model.containsAttribute(ACCOUNT)) {
            model.addAttribute(ACCOUNT, new AccountDTO());
            model.addAttribute(CURRENCIES, currencyService.list());
        }
        return FORWARD_ACCOUNT_ADD;
    }

    /**
     * Wywoła, gdy użytkownik kliknnie dodaj link na stronie dodaj konto.
     *
     * @param request o httpservletRequest
     * @param accountDTO AccountDTO
     * @param redirectAttrs atrybuty przekierowania
     * @return strona z szablonu REDIRECT_ACCOUNT_LIST
     * @throws Exception, jeśli wystąpi wyjątek
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, @ModelAttribute AccountDTO accountDTO,
                      RedirectAttributes redirectAttrs) throws Exception {
        Account account = accountDTO.toAccount();
        User user = getUser();
        account.setCreatedAt(LocalDateTime.now());
        account.setUserId(user.getId());
        accountService.create(account);

        String message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_ADD,
                new Object[]{ACCOUNT, account.getName()});
        redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);

        return REDIRECT_ACCOUNT_LIST;
    }

    /**
     * Wywołane, gdy użytkownik kliknie na stronie edytuj link ze strony kont.
     *
     * @param id Identyfikator konta
     * @param model Model
     * @return strona z szablonu FORWARD_ACCOUNT_EDIT
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(@RequestParam(value = "id") long id, Model model) {
        if (!model.containsAttribute(ACCOUNT)) {
            User user = getUser();
            Account account = accountService.get(id, user.getId());
            model.addAttribute(ACCOUNT, AccountDTO.from(account));
            model.addAttribute(CURRENCIES, currencyService.list());
        }
        return FORWARD_ACCOUNT_EDIT;
    }

    /**
     * Wywołam, gdy użytkownik kliknie link edytuj na stronie edycja konta.
     *
     * @param request o httpservletRequest
     * @param accountDTO AccountDTO
     * @param redirectAttrs atrybuty przekierowania
     * @return strona z szablonu REDIRECT_ACCOUNT_LIST
     * @throws Exception jeśli wystąpi wyjątek
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest request, @ModelAttribute AccountDTO accountDTO,
                       RedirectAttributes redirectAttrs) throws Exception {
        Account account = accountDTO.toAccount();
        User user = getUser();
        account.setUserId(user.getId());

        accountService.update(account);

        String message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_UPDATE,
                new Object[]{ACCOUNT, account.getName()});
        redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);

        return REDIRECT_ACCOUNT_LIST;
    }

    /**
     * Wywołam, gdy użytkownik kliknie link do usuwania konta.
     *
     * @param request  httpServletRequest
     * @param id identyfikator konta
     * @param redirectAttrs atrybuty przekierowania
     * @return strona z szablonu REDIRECT_ACCOUNT_LIST
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, @RequestParam(value = "id") long id,
                         RedirectAttributes redirectAttrs) {
        try {
            if (id != 0) {
                User user = getUser();
                Account account = accountService.get(id, user.getId());
                accountService.delete(id, user.getId());

                String message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_DELETE,
                        new Object[]{ACCOUNT, account.getName()});
                redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);
            }
        } catch (CannotDeleteException e) {
            String message = i18n.msg(request, I18n.KEY_ERROR_ACCOUNT_DELETE);
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
        }

        return REDIRECT_ACCOUNT_LIST;
    }

    /**
     * Wywołane, gdy użytkownik kliknie link Zapisz z okna dialogowego Dostosuj saldo.
     *
     * @param adjustBalance AdjustBalance
     * @return strona z szablonu REDIRECT_ACCOUNT_LIST
     */
    @RequestMapping(value = "/adjustBalance", method = RequestMethod.POST)
    public String adjustBalance(@ModelAttribute AdjustBalanceDTO adjustBalance) {
        adjustBalance.setUserId(getUser().getId());
        accountService.updateBalance(adjustBalance);

        return REDIRECT_ACCOUNT_LIST;
    }
}