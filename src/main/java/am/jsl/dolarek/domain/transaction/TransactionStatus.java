package am.jsl.dolarek.domain.transaction;

/**
 * Wyliczenie zawierające możliwe statusy transakcji.
 */
public enum TransactionStatus {
  PENDING((byte) 1),
  DONE((byte) 2);

  /*
   * Wartość
   */
  private byte value;

  TransactionStatus(byte value) {
    this.value = value;
  }

  /**
   * Gets the value of the value property.
   */
  public byte getValue() {
    return value;
  }
}
