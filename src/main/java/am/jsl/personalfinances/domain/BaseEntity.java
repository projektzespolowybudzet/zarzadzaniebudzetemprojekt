package am.jsl.personalfinances.domain;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
*Obiekt domeny o wspólnych właściwościach.
*Używana jako klasa bazowa dla obiektów wymagających tych właściwości.
*/
public class BaseEntity implements Serializable {

    private long id;
    protected long userId;
    protected LocalDateTime createdAt = null;
    protected long changedBy = 0;
    protected LocalDateTime changedAt = null;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getChangedBy() {
        return changedBy;
    }
    public void setChangedBy(long changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }
    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d)", this.getClass().getSimpleName(), this.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)
            return false;

        if (o instanceof BaseEntity) {
            final BaseEntity other = (BaseEntity) o;
            return Objects.equals(getId(), other.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
