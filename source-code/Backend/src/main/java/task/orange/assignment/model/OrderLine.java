package task.orange.assignment.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "order_line_tbl")
public class OrderLine {
    
    @JsonIgnore
    @EmbeddedId
    OrderLineKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("orderIdFK")
    @JoinColumn(name = "order_id_fk")
    Order order;

    @ManyToOne
    @MapsId("productIdFK")
    @JoinColumn(name = "product_id_fk")
    Product product;

    @Column(
        name = "product_quantity",
        nullable = false
    )
    int productQuantity;

    public OrderLine(){}

    public OrderLine(OrderLineKey id, Order order, Product product, int productQuantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.productQuantity = productQuantity;
    }

    public OrderLineKey getId() {
        return id;
    }

    public void setId(OrderLineKey id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + productQuantity;
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
        OrderLine other = (OrderLine) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (productQuantity != other.productQuantity)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrderLine [id=" + id + ", order=" + order + ", product=" + product + ", productQuantity="
                + productQuantity + "]";
    }
}
