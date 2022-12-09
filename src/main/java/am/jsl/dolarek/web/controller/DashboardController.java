package am.jsl.dolarek.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import am.jsl.dolarek.dto.account.AccountListDTO;
import am.jsl.dolarek.dto.transaction.TransactionByCategoryResultDTO;
import am.jsl.dolarek.search.transaction.TransactionByCategorySearchQuery;
import am.jsl.dolarek.service.account.AccountService;
import am.jsl.dolarek.service.transaction.TransactionService;
import am.jsl.dolarek.util.Constants;
import am.jsl.dolarek.util.DateUtils;
import am.jsl.dolarek.web.form.PieChartForm;
import am.jsl.dolarek.web.util.JsonResponse;

import java.util.Date;
import java.util.List;

/**
 * Definiuje metody funkcjonalności strony deski rozdzielczej.
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private transient TransactionService transactionService;

    @Autowired
    private transient AccountService accountService;

    /**
     * Wyszukuje transakcje dla bieżącego użytkownika na podstawie podanych danych PiechartForm
     * i zwraca JSON.
     *
     * @param searchForm PieChartForm
     * @return json z danymi pie chart
     */
    @RequestMapping(value = {"/pieChartData"}, method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse pieChartData(@ModelAttribute PieChartForm searchForm) {
        Date start = null;
        Date end = null;
        String[] str = searchForm.getDaterange().split("-");

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
        JsonResponse response = new JsonResponse();

        // init search query
        TransactionByCategorySearchQuery query = new TransactionByCategorySearchQuery();
        query.setUserId(getUser().getId());
        query.setTransactionType(searchForm.getType());
        query.setAccountId(searchForm.getAccount());
        query.setStartDate(start);
        query.setEndDate(end);

        TransactionByCategoryResultDTO result = transactionService.search(query);
        response.setResult(result);

        return response;
    }

    /**
     * Zwraca listę aktywnych kont dla bieżącego użytkownika.
     * @return lista aktywnych kont 
     */
    @RequestMapping(value = {"/accountsOverview"}, method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse accountsOverview() {
        JsonResponse response = new JsonResponse();
        List<AccountListDTO> accounts = accountService.getActiveAccounts(getUser().getId());
        response.setResult(accounts);

        return response;
    }
}
