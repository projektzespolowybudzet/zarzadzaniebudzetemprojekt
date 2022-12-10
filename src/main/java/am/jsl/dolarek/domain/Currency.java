package am.jsl.dolarek.domain;

import java.io.Serializable;

/**
*Obiekt domeny waluty.
*/
public class Currency implements Serializable {

    private String code;
    private String name;
    private String symbol;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
