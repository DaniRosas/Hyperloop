package com.hyperloop.uploadapp.upload;

/**
 * Created by DaniRosas on 31/7/17.
 */

public class Category {
    private String category;
    private boolean position;
    private boolean isSelected;

    public Category(String category, boolean isSelected) {
        this.category = category;
        this.isSelected = isSelected;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPosition() {
        return position;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
