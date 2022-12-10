package am.jsl.dolarek.dto.transaction;

import java.io.Serializable;
import java.util.List;

/**
*Zawiera listę transakcji pogrupowanych według kategorii i rachunku.
*/
public class TransactionByCategoryResultDTO implements Serializable {

    private String totalStr;
    private List<TransactionByCategoryDTO> list;

    public String getTotalStr() {
        return totalStr;
    }
    public void setTotalStr(String totalStr) {
        this.totalStr = totalStr;
    }

    public List<TransactionByCategoryDTO> getList() {
        return list;
    }
    public void setList(List<TransactionByCategoryDTO> list) {
        this.list = list;
    }
}
