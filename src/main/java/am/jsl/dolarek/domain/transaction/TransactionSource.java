package am.jsl.dolarek.domain.transaction;

import java.io.Serializable;

/**
*Wyliczenie zawierające możliwe źródła transakcji.
*/
public enum TransactionSource implements Serializable {

    MANUAL((byte) 1);

    private byte value;

    TransactionSource(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

}
