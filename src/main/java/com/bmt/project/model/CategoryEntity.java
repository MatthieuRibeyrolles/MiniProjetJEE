package com.bmt.project.model;

import java.util.Objects;

/**
 *
 * @author Thibault
 */
public class CategoryEntity {

    private int code;
    private String wording;
    private String desc;

    public CategoryEntity(int code, String wording, String desc) {
        this.code = code;
        this.wording = wording;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.code;
        hash = 53 * hash + Objects.hashCode(this.wording);
        hash = 53 * hash + Objects.hashCode(this.desc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CategoryEntity other = (CategoryEntity) obj;
        if (this.code != other.code)
            return false;
        if (!Objects.equals(this.wording, other.wording))
            return false;
        if (!Objects.equals(this.desc, other.desc))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" + "code=" + code + ", wording=" + wording + ", desc=" + desc + '}';
    }

}
