package task.orange.assignment.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "supplier_has_product_tpl")
public class SupplierHasProduct {
    
    @EmbeddedId
    SupplierHasProductKey id;

    @ManyToOne
    @MapsId("supplierIdFK")
    @JoinColumn(name = "supplier_id_fk")
    Supplier supplier;

    @ManyToOne
    @MapsId("productIdFK")
    @JoinColumn(name = "product_id_fk")
    Product product;

    public SupplierHasProduct(){}

    public SupplierHasProduct(Supplier supplier, Product product) {
        
        this.supplier = supplier;
        this.product = product;
    }

    public SupplierHasProduct(SupplierHasProductKey id, Supplier supplier, Product product) {
        this.id = id;
        this.supplier = supplier;
        this.product = product;
    }

    public SupplierHasProductKey getId() {
        return id;
    }

    public void setId(SupplierHasProductKey id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((supplier == null) ? 0 : supplier.hashCode());
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
        SupplierHasProduct other = (SupplierHasProduct) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (supplier == null) {
            if (other.supplier != null)
                return false;
        } else if (!supplier.equals(other.supplier))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SupplierHasProduct [id=" + id + ", product=" + product + ", supplier=" + supplier + "]";
    }
}
