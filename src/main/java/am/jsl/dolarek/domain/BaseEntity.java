package am.jsl.dolarek.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Obiekt domeny o wspólnych właściwościach.
 * Używana jako klasa bazowa dla obiektów wymagających tych właściwości.
 */
public class BaseEntity implements Serializable {

  private long id;
  protected long userId;
  protected LocalDateTime createdAt = null;
  protected long changedBy = 0;
  protected LocalDateTime changedAt = null;

  /**
   * Gets the value of the id property.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id of the object.
   *
   * @param id - The new value of id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the value of the userId property.
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Sets the userId value for this User.
   *
   * @param userId - The userId of the user to be used for the user.
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Gets the value of the createdAt property.
   */
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the createdAt attribute value.
   *
   * @param createdAt - The createdAt value for this InvoiceParamsOutC.
   */
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the value of the changedBy property.
   */
  public long getChangedBy() {
    return changedBy;
  }

  /**
   * Sets the value of changedBy.
   *
   * @param changedBy - The amount of time the user has changed.
   */
  public void setChangedBy(long changedBy) {
    this.changedBy = changedBy;
  }

  /**
   * Gets the value of the changedAt property.
   */
  public LocalDateTime getChangedAt() {
    return changedAt;
  }

  /**
   * Sets the changedAt attribute value.
   *
   * @param changedAt - The changedAt value for this ReportMonthBO.
   */
  public void setChangedAt(LocalDateTime changedAt) {
    this.changedAt = changedAt;
  }

  /**
   * Returns a string representation of this object.
   */
  @Override
  public String toString() {
    return String.format(
      "%s(id=%d)",
      this.getClass().getSimpleName(),
      this.getId()
    );
  }

  /**
   * Compares this Entity with another Entity.
   *
   * @param o - the object to compare this entity with
   */
  @Override
  public boolean equals(Object o) {
    /**
     *Returns true if this object is the same as the receiver.
     */
    if (this == o) return true;
    /**
     *Returns true if the object is null.
     */
    if (o == null) return false;

    /**
     *Checks if this entity is a base entity.
     */
    if (o instanceof BaseEntity) {
      final BaseEntity other = (BaseEntity) o;
      return Objects.equals(getId(), other.getId());
    }
    return false;
  }

  /**
   * Returns a hashCode for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }
}
