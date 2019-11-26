package com.bmt.project.model;

import java.util.Objects;

/**
 *
 * @author Thibault
 */
public class Line {
    
    private OrderEntity order;
    private ProductEntity product;
    private int qty;

    public Line(OrderEntity order, ProductEntity product, int qty) {
        this.order = order;
        this.product = product;
        this.qty = qty;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.order);
        hash = 73 * hash + Objects.hashCode(this.product);
        hash = 73 * hash + this.qty;
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
        final Line other = (Line) obj;
        if (this.qty != other.qty) {
            return false;
        }
        if (!Objects.equals(this.order, other.order)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Line{" + "order=" + order + ", product=" + product + ", qty=" + qty + '}';
    }
    
}
