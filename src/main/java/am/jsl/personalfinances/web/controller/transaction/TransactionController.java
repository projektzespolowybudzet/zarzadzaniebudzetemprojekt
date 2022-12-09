package am.jsl.personalfinances.web.controller.transaction;

import am.jsl.personalfinances.domain.account.Account;
import am.jsl.personalfinances.domain.transaction.Transaction;
import am.jsl.personalfinances.domain.transaction.TransactionType;
import am.jsl.personalfinances.domain.user.User;
import am.jsl.personalfinances.dto.transaction.*;
import am.jsl.personalfinances.ex.CannotDeleteException;
import am.jsl.personalfinances.search.ListPaginatedResult;
import am.jsl.personalfinances.search.PageWrapper;
import am.jsl.personalfinances.search.transaction.TransactionSearchQuery;
import am.jsl.personalfinances.service.account.AccountService;
import am.jsl.personalfinances.service.transaction.TransactionService;
import am.jsl.personalfinances.util.Constants;
import am.jsl.personalfinances.util.DateUtils;
import am.jsl.personalfinances.web.controller.BaseController;
import am.jsl.personalfinances.web.form.TransactionSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static am.jsl.personalfinances.web.util.WebUtils.*;

/**
 * TransactionController definiuje metody funkcjonalności stron transakcyjnych
 * takie jak wyszukiwanie, widok, dodaj, edytuj transakcje.
 */
@Controller
@RequestMapping(value = "/transaction")
@Lazy
public class TransactionController extends BaseController {
    /**
     * Ścieżki szablonów transakcji
     */
    public static final String REDIRECT_TRANSACTION_LIST = "redirect:list";
    public static final String FORWARD_TRANSACTION_LIST = "transaction/transaction-list";
    public static final String FORWARD_TRANSACTION_RESULT_LIST = "transaction/transaction-list-result :: transactionResultsList";
    public static final String FORWARD_TRANSACTION_VIEW = "transaction/transaction-view :: transactionFormDiv";
    public static final String FORWARD_TRANSACTION_MANAGE = "transaction/transaction-manage :: transactionFormDiv";
    public static final String FORWARD_TRANSACTION_ADD_BATCH = "transaction/transaction-add-batch :: transactionFormDiv";

    public static String ADD_TRANSACTION = "addTransaction";

    @Autowired
    private transient AccountService accountService;

    @Autowired
    private transient TransactionService transactionService;

    /**
     * Wywołany po załadowaniu listy transakcji.
     * @return Lista transakcji
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String list() {
        return FORWARD_TRANSACTION_LIST;
    }

    /**
     * Nazywany metodą AJAX do wyszukiwania transakcji na podstawie danych TransactionesearchForm.
     * @param model Model
     * @param searchForm TransactioneSechForm
     * @return strona z szablonu FORWARD_TRANSACTION_RESULT_LIST
     */
    @RequestMapping(value = {"/loadTransactions"}, method = RequestMethod.POST)
    public String loadTransactions(Model model,
                                   @ModelAttribute TransactionSearchForm searchForm) {

        int page = searchForm.getPage();
        int size = searchForm.getSize();
        Date start = null;
        Date end = null;
        String[] str = searchForm.getDaterange().split("-");

        if (str.length == 2) {
            try {
                start = DateUtils.toDate(str[0].trim(), Constants.DATE_FORMAT_L);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

            try {
                end = DateUtils.toDate(str[1].trim(), Constants.DATE_FORMAT_L);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        if (size < Constants.PAGE_SIZE || size > 100) {
            size = Constants.PAGE_SIZE;
        }

        // inicjacja search query
        TransactionSearchQuery query = new TransactionSearchQuery(page, size);
        query.setUserId(getUser().getId());
        query.setTransactionType(searchForm.getType());
        query.setAccountId(searchForm.getAccount());
        query.setCategoryId(searchForm.getCategory());
        query.setDescription(searchForm.getDescription());
        query.setSortBy(searchForm.getSortBy());
        query.setAsc(searchForm.isAsc());
        query.setStartDate(start);
        query.setEndDate(end);

        TransactionSearchResult result = transactionService.search(query);
        ListPaginatedResult<TransactionListDTO> paginatedResult = result.getListPaginatedResult();

        PageWrapper<TransactionListDTO> pageWrapper = new PageWrapper<>(paginatedResult, page, size);
        model.addAttribute(Constants.PAGE, pageWrapper);
        model.addAttribute(SEARCH_TOTALS, result.getTotals());

        return FORWARD_TRANSACTION_RESULT_LIST;
    }

    /**
     * Wywołane, gdy użytkownik kliknie Transakcje ze strony Transakcji.
     * @param model Model
     * @return strona z szablonu FORWARD_TRANSACTION_MANAGE
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        if (!model.containsAttribute(TRANSACTION)) {
            User user = getUser();
            long userId = user.getId();
            List<Account> accounts = accountService.lookup(userId);
            long accountId = !accounts.isEmpty() ? accounts.get(0).getId() : 0;

            TransactionDTO transactionDTO = new TransactionDTO(accountId, TransactionType.EXPENSE.getValue(), LocalDateTime.now(), userId);
            transactionDTO.setTargetAccountId(accountId);
            model.addAttribute(TRANSACTION, transactionDTO);
            model.addAttribute(ACCOUNTS, accounts);
        }
        return FORWARD_TRANSACTION_MANAGE;
    }

    /**
     * Wywołane, gdy użytkownik kliknie Edytuj ze strony listy transakcji.
     * @param id identyfikator transakcji
     * @param model Model
     * @return strona z szablonu FORWARD_TRANSACTION_MANAGE
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(@RequestParam(value = "id") long id, Model model) {
        if (!model.containsAttribute(TRANSACTION)) {
            User user = getUser();
            long userId = user.getId();
            Transaction transaction = transactionService.get(id, userId);
            List<Account> accounts = accountService.lookup(userId);

            model.addAttribute(TRANSACTION, TransactionDTO.from(transaction));
            model.addAttribute(ACCOUNTS, accounts);
        }
        return FORWARD_TRANSACTION_MANAGE;
    }

    /**
     * Wywołane, gdy użytkownik kliknie Zapisz ze strony zarządzania transakcją.
     * @param transakcja
     * @return Strona listy transakcji
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = transactionDTO.toTransaction();
        transaction.setUserId(getUser().getId());

        if (transaction.getId() == 0) {
            transactionService.create(transaction);
        } else {
            transactionService.update(transaction);
        }

        return REDIRECT_TRANSACTION_LIST;
    }

    /**
     * Wywołane, gdy użytkownik kliknie na Dodaj ze strony listy transakcji.
     * @param model Model
     * @return strona z szablonu FORWARD_TRANSACTION_ADD_BATCH
     */
    @RequestMapping(value = "/addBatch", method = RequestMethod.GET)
    public String addBatchPage(Model model) {
        if (!model.containsAttribute(ADD_TRANSACTION)) {
            User user = getUser();
            long userId = user.getId();
            List<Account> accounts = accountService.lookup(userId);
            long accountId = !accounts.isEmpty() ? accounts.get(0).getId() : 0;

            AddTransactionsDTO addTransactionsDTO = new AddTransactionsDTO(accountId, TransactionType.EXPENSE.getValue(), LocalDateTime.now(), userId);
            addTransactionsDTO.addTransaction(new Transaction());

            model.addAttribute(ADD_TRANSACTION, addTransactionsDTO);
            model.addAttribute(ACCOUNTS, accounts);
        }
        return FORWARD_TRANSACTION_ADD_BATCH;
    }

    /**
     * Wywołane, gdy użytkownik kliknie Zapisz z okna dodania transakcji.
     * @param addTransactionsDTO AddTransactionsDTO
     * @return strona z szablonu REDIRECT_TRANSACTION_LIST
     */
    @RequestMapping(value = "/addBatch", method = RequestMethod.POST)
    public String addBatch(@ModelAttribute AddTransactionsDTO addTransactionsDTO) {
        addTransactionsDTO.setUserId(getUser().getId());
        transactionService.createBatch(addTransactionsDTO);

        return REDIRECT_TRANSACTION_LIST;
    }

    /**
     * Wywołane, gdy użytkownik kliknie Edytuj ze strony listy transakcji.
     * @param id identyfikator transakcji 
     * @param model Model 
     * @return strona z szablonu FORWARD_TRANSACTION_VIEW
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam(value = "id", required = true)
                                   long id, Model model) {
        if (!model.containsAttribute(TRANSACTION)) {
            User user = getUser();
            long userId = user.getId();
            TransactionDetailsDTO transaction = transactionService.getDetails(id, userId);

            model.addAttribute(TRANSACTION, transaction);
        }
        return FORWARD_TRANSACTION_VIEW;
    }

    /**
     * Wywołane, gdy użytkownik kliknie Usuń na stronie tranzakcji.
     * @param id identyfikator transakcji do usunięcia
     * @return strona z szablonu REDIRECT_TRANSACTION_LIST
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "id", required = true) long id) {

        try {
            if (id != 0) {
                transactionService.delete(id, getUser().getId());
            }
        } catch (CannotDeleteException e) {
            log.error(e.getMessage(), e);
        }

        return REDIRECT_TRANSACTION_LIST;
    }
}