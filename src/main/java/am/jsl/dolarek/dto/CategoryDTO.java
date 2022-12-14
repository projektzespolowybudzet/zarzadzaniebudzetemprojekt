package am.jsl.dolarek.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO extends DescriptiveDTO {
    private String icon;
    private String color;
    private long parentId;
    private List<CategoryDTO> childs;

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

    public List<CategoryDTO> getChilds() {
        return childs;
    }
    public void setChilds(List<CategoryDTO> childs) {
        this.childs = childs;
    }
    public void addChild(CategoryDTO category) {
        if (childs == null) {
            childs = new ArrayList<>();
        }
        childs.add(category);
    }
}
