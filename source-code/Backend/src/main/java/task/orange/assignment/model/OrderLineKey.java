package task.orange.assignment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderLineKey implements Serializable{
    
    @Column(name = "order_id_fk")
    Long orderIdFK;

    @Column(name = "product_id_fk")
    Long productIdFK;

    public OrderLineKey(){}

    public OrderLineKey(Long orderIdFK, Long productIdFK) {
        this.orderIdFK = orderIdFK;
        this.productIdFK = productIdFK;
    }

    public Long getOrderIdFK() {
        return orderIdFK;
    }

    public void setOrderIdFK(Long orderIdFK) {
        this.orderIdFK = orderIdFK;
    }

    public Long getProductIdFK() {
        return productIdFK;
    }

    public void setProductIdFK(Long productIdFK) {
        this.productIdFK = productIdFK;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderIdFK == null) ? 0 : orderIdFK.hashCode());
        result = prime * result + ((productIdFK == null) ? 0 : productIdFK.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderLineKey other = (OrderLineKey) obj;
        if (orderIdFK == null) {
            if (other.orderIdFK != null)
                return false;
        } else if (!orderIdFK.equals(other.orderIdFK))
            return false;
        if (productIdFK == null) {
            if (other.productIdFK != null)
                return false;
        } else if (!productIdFK.equals(other.productIdFK))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrderLineKey [orderIdFK=" + orderIdFK + ", productIdFK=" + productIdFK + "]";
    }
}
