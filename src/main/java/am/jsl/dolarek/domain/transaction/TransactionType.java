package am.jsl.dolarek.domain.transaction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Wyliczenie zawierające możliwe typy transakcji.
 */
public enum TransactionType {
  EXPENSE((byte) 1),
  INCOME((byte) 2),
  TRANSFER((byte) 3);

  private byte value;
  private static final Map<Byte, TransactionType> types;

  static {
    types = new HashMap<>();

    Arrays.stream(values()).forEach(type -> types.put(type.getValue(), type));
  }

  /**
   * Returns the TransactionType for the given byte value.
   *
   * @param value - the value to get the type for
   */
  public static TransactionType get(byte value) {
    return types.get(value);
  }

  TransactionType(byte value) {
    this.value = value;
  }

  /**
   * Gets the value of the value property.
   */
  public byte getValue() {
    return value;
  }
}
