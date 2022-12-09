package am.jsl.personalfinances.domain;


import java.io.Serializable;
import java.util.Objects;

/**
*Obiekt domeny kategorii.
*/
public class Category extends Descriptive implements Serializable {
 
    public static final String DEFAULT_COLOR= "#a0a0a0";
    private String icon;
    private String color;
    private long parentId;

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public long getParentId() {
        return parentId;
    }
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Category) {
            final Category other = (Category) o;
            return Objects.equals(getId(), other.getId())
                    && Objects.equals(getName(), other.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
