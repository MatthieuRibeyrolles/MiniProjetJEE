package com.bmt.project.model;

import java.util.Objects;

/**
 *
 * @author Thibault
 */
public class ProductEntity {
    
    private int reference;
    private String name;
    private int provider;
    private CategoryEntity category;
    private String qtyPerPackage;
    private float price;
    private int stock;
    private int ordered;
    private int refill;
    private boolean available;

    public ProductEntity() {
    }

    public ProductEntity(int reference, String name, int provider, CategoryEntity category, String qtyPerPackage, float price, int stock, int ordered, int refill, boolean available) {
        this.reference = reference;
        this.name = name;
        this.provider = provider;
        this.category = category;
        this.qtyPerPackage = qtyPerPackage;
        this.price = price;
        this.stock = stock;
        this.ordered = ordered;
        this.refill = refill;
        this.available = available;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getQtyPerPackage() {
        return qtyPerPackage;
    }

    public void setQtyPerPackage(String qtyPerPackage) {
        this.qtyPerPackage = qtyPerPackage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public int getRefill() {
        return refill;
    }

    public void setRefill(int refill) {
        this.refill = refill;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.reference;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + this.provider;
        hash = 59 * hash + Objects.hashCode(this.category);
        hash = 59 * hash + Objects.hashCode(this.qtyPerPackage);
        hash = 59 * hash + Float.floatToIntBits(this.price);
        hash = 59 * hash + this.stock;
        hash = 59 * hash + this.ordered;
        hash = 59 * hash + this.refill;
        hash = 59 * hash + (this.available ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductEntity other = (ProductEntity) obj;
        if (this.reference != other.reference) {
            return false;
        }
        if (this.provider != other.provider) {
            return false;
        }
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
            return false;
        }
        if (this.stock != other.stock) {
            return false;
        }
        if (this.ordered != other.ordered) {
            return false;
        }
        if (this.refill != other.refill) {
            return false;
        }
        if (this.available != other.available) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.qtyPerPackage, other.qtyPerPackage)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductEntity{" + "reference=" + reference + ", name=" + name + ", provider=" + provider + ", category=" + category + ", qtyPerPackage=" + qtyPerPackage + ", price=" + price + ", stock=" + stock + ", ordered=" + ordered + ", refill=" + refill + ", available=" + available + '}';
    }
    
}
