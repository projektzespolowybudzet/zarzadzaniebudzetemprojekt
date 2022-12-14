package am.jsl.dolarek.domain;

import java.io.Serializable;

/**
 * Obiekt domeny waluty.
 */
public class Currency implements Serializable {

  private String code;
  private String name;
  private String symbol;

  /**
   * Gets the value of the code property.
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the code of the message.
   *
   * @param code - The code of the message.
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Gets the value of the name property.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the object.
   *
   * @param name - The name of the new name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the value of the symbol property.
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Sets the symbol of the symbol.
   *
   * @param symbol - The symbol of the symbol.
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
