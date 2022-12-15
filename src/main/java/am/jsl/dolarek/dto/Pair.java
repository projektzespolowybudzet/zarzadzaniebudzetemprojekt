package am.jsl.dolarek.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Klasa par jest używana do przechowywania parametrowanych par kluczowych.
 */
public class Pair<K, V> implements Serializable {

  private K key = null;
  private V value = null;

  /**
   * Domyślny konstruktor
   */
  public Pair() {}

  /**
   * Konstruuje nowe pary z danym kluczem i wartością.
   * @param key parametryczny klucz
   * @param value parametryzowana wartość
   */
  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Getter for property 'key'.
   *
   * @return Value for property 'key'.
   */
  public K getKey() {
    return key;
  }

  /**
   * Setter for property 'key'.
   *
   * @param key Value to set for property 'key'.
   */
  public void setKey(K key) {
    this.key = key;
  }

  /**
   * Getter for property 'value'.
   *
   * @return Value for property 'value'.
   */
  public V getValue() {
    return value;
  }

  /**
   * Setter for property 'value'.
   *
   * @param value Value to set for property 'value'.
   */
  public void setValue(V value) {
    this.value = value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Pair other = (Pair) obj;
    return (
      Objects.equals(this.key, other.key) &&
      Objects.equals(this.value, other.value)
    );
  }
}
