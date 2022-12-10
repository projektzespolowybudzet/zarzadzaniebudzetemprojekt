package am.jsl.dolarek.web.controller.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import am.jsl.dolarek.dto.Pair;
import am.jsl.dolarek.service.currency.CurrencyService;
import am.jsl.dolarek.util.TextUtils;
import am.jsl.dolarek.web.controller.BaseController;
import am.jsl.dolarek.web.util.JsonResponse;

/**
 * Definiuje metody przekształcania walut.
 */
@Controller
@RequestMapping(value = "/currency")
@Lazy
public class CurrencyController extends BaseController {
    /**
     * The CurrencyService
     */
    @Autowired
    private transient CurrencyService currencyService;

    /**
     * Przekształca daną kwotę z waluty źródłowej na walutę docelową.
     * @param amount wartość
     * @param from waluta źródłowa
     * @param to waluta docelowa
     * @return json z parą stawka, kwota w nowej walucie
     */
    @RequestMapping(value = {"/convert"}, method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse convert(@RequestParam(value = "amount") double amount,
                         @RequestParam(value = "from") String from,
                         @RequestParam(value = "to") String to) {
        JsonResponse response = new JsonResponse();

        if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(to)) {
            double rate = currencyService.getRate(from, to);
            amount =  amount * rate;

            Pair<Double, Double> rateAmount = new Pair<>(rate, amount);
            response.setResult(rateAmount);
        }

        return response;
    }

    /**
     * Przekształca daną kwotę z daną stawką.
     * @param amount wartość
     * @param rate stawka
     * @return kwota w nowej walucie
     */
    @RequestMapping(value = {"/convertWithRate"}, method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse convertWithRate(@RequestParam(value = "amount") double amount,
                         @RequestParam(value = "rate") double rate) {
        JsonResponse response = new JsonResponse();
        response.setResult(amount * rate);
        return response;
    }
}
