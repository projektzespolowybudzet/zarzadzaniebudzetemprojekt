package am.jsl.dolarek.dto.transaction;

import am.jsl.dolarek.search.ListPaginatedResult;

import java.io.Serializable;
import java.util.List;

/**
*TransactionSearchResult zawiera listę transakcji i łączne kwoty
*pogrupowane według rodzaju transakcji i rachunku.
*@see TransactionListTotalDTO
*@see TransactionListDTO
*/
public class TransactionSearchResult implements Serializable {
    private List<TransactionListTotalDTO> totals;
    private ListPaginatedResult<TransactionListDTO> listPaginatedResult;

    public List<TransactionListTotalDTO> getTotals() {
        return totals;
    }
    public void setTotals(List<TransactionListTotalDTO> totals) {
        this.totals = totals;
    }

    public ListPaginatedResult<TransactionListDTO> getListPaginatedResult() {
        return listPaginatedResult;
    }
    public void setListPaginatedResult(ListPaginatedResult<TransactionListDTO> listPaginatedResult) {
        this.listPaginatedResult = listPaginatedResult;
    }
}
