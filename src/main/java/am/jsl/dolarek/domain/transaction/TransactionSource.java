package am.jsl.dolarek.domain.transaction;


/**
*Wyliczenie zawierające możliwe źródła transakcji.
*/
public enum TransactionSource {

    MANUAL((byte) 1);

    private byte value;

    TransactionSource(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

}
