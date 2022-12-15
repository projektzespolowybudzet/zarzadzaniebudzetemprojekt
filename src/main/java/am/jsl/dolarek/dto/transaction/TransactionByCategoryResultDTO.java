package am.jsl.dolarek.dto.transaction;

import java.io.Serializable;
import java.util.List;

/**
 *Zawiera listę transakcji pogrupowanych według kategorii i rachunku.
 */
public class TransactionByCategoryResultDTO implements Serializable {

  /**
   * Całkowita kwota
   */
  private String totalStr;

  /**
   * Lista zgrupowanych transakcji
   */
  private List<TransactionByCategoryDTO> list;

  /**
   * Getter for property 'totalStr'.
   *
   * @return Value for property 'totalStr'.
   */
  public String getTotalStr() {
    return totalStr;
  }

  /**
   * Setter for property 'totalStr'.
   *
   * @param totalStr Value to set for property 'totalStr'.
   */
  public void setTotalStr(String totalStr) {
    this.totalStr = totalStr;
  }

  /**
   * Getter for property 'list'.
   *
   * @return Value for property 'list'.
   */
  public List<TransactionByCategoryDTO> getList() {
    return list;
  }

  /**
   * Setter for property 'list'.
   *
   * @param list Value to set for property 'list'.
   */
  public void setList(List<TransactionByCategoryDTO> list) {
    this.list = list;
  }
}
