package task.orange.assignment.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "order_tbl")
@ApiModel(description = "Order model represents the properties and the behaviour of an order.\n" +
        "Note that orderId is unique for each order and it is generated automatically.")
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
        name = "order_id",
        updatable = false,
        nullable = false
    )
    @ApiModelProperty(notes = "The unique id of the order (generated automatically)")
    private Long orderId; 

    @Column(
        name = "date_order",
        nullable = false
    )
    @ApiModelProperty(notes = "The order's date")
    private Date dateOrder;

    @Column(
        name = "is_done",
        nullable = false
    )
    @ApiModelProperty(notes = "Is order done")
    private Boolean isDone; 

    @Column(
        name = "total_amount",
        nullable = false
    )
    @ApiModelProperty(notes = "The order's total amount")
    private Double totalAmount;
    
    @OneToMany(
        mappedBy = "order", 
        cascade = CascadeType.ALL,
        orphanRemoval = true                
    )
    private List < OrderLine > orderLines;

    protected Order() {}

    public Order(Date dateOrder, Boolean isDone, Double totalAmount, List<OrderLine> orderLines) {
        this.dateOrder = dateOrder;
        this.isDone = isDone;
        this.totalAmount = totalAmount;
        this.orderLines = orderLines;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dateOrder == null) ? 0 : dateOrder.hashCode());
        result = prime * result + ((isDone == null) ? 0 : isDone.hashCode());
        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
        result = prime * result + ((orderLines == null) ? 0 : orderLines.hashCode());
        result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
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
        Order other = (Order) obj;
        if (dateOrder == null) {
            if (other.dateOrder != null)
                return false;
        } else if (!dateOrder.equals(other.dateOrder))
            return false;
        if (isDone == null) {
            if (other.isDone != null)
                return false;
        } else if (!isDone.equals(other.isDone))
            return false;
        if (orderId == null) {
            if (other.orderId != null)
                return false;
        } else if (!orderId.equals(other.orderId))
            return false;
        if (orderLines == null) {
            if (other.orderLines != null)
                return false;
        } else if (!orderLines.equals(other.orderLines))
            return false;
        if (totalAmount == null) {
            if (other.totalAmount != null)
                return false;
        } else if (!totalAmount.equals(other.totalAmount))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", dateOrder=").append(dateOrder);
        sb.append(", isDone=").append(isDone);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", orderLines=").append(orderLines);
        sb.append('}');
        return sb.toString();
    }
}
