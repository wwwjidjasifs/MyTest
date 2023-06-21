package com.Pojo;

import java.util.List;
public class Menus {
    private String id;//菜单id
    private String title;//菜单名字
    private String href;//链接
    private String field;//字段
    private boolean spread=false;//是否展开
    private boolean checked=false;//是否选中
    private List<Menus> children;//子菜单

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<Menus> getChildren() {
        return children;
    }

    public void setChildren(List<Menus> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menus{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", field='" + field + '\'' +
                ", spread=" + spread +
                ", checked=" + checked +
                ", children=" + children +
                '}';
    }
}
