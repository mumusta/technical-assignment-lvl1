package task.orange.assignment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SupplierHasProductKey implements Serializable{
    
    @Column(name = "supplier_id_fk")
    Long supplierIdFK;

    @Column(name = "product_id_fk")
    Long productIdFK;

    public SupplierHasProductKey(){}

    public SupplierHasProductKey(Long supplierIdFK, Long productIdFK) {
        this.supplierIdFK = supplierIdFK;
        this.productIdFK = productIdFK;
    }

    public Long getSupplierIdFK() {
        return supplierIdFK;
    }

    public void setSupplierIdFK(Long supplierIdFK) {
        this.supplierIdFK = supplierIdFK;
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
        result = prime * result + ((productIdFK == null) ? 0 : productIdFK.hashCode());
        result = prime * result + ((supplierIdFK == null) ? 0 : supplierIdFK.hashCode());
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
        SupplierHasProductKey other = (SupplierHasProductKey) obj;
        if (productIdFK == null) {
            if (other.productIdFK != null)
                return false;
        } else if (!productIdFK.equals(other.productIdFK))
            return false;
        if (supplierIdFK == null) {
            if (other.supplierIdFK != null)
                return false;
        } else if (!supplierIdFK.equals(other.supplierIdFK))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SupplierHasProductKey [productIdFK=" + productIdFK + ", supplierIdFK=" + supplierIdFK + "]";
    }
}
