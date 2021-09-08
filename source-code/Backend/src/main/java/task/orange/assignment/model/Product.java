package task.orange.assignment.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity(name = "product_tbl")
@ApiModel(description = "Product model represents the properties and the behaviour of a product.\n" +
        "Note that productId and name are unique for each product, and productId is generated automatically.")
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
        name = "product_id",
        updatable = false,
        nullable = false
    )
    @ApiModelProperty(notes = "The unique id of the product (generated automatically)")
    private Long productId;

    @Column(
        name = "name",
        nullable = false,
        length = 45,
        unique = true
    )
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 45, message = "Name must be between 3 and 45 characters inclusive")
    @Pattern(message = "Name is not valid (follow this regex: ^[a-zA-Z0-9\\s]+$)", regexp = "^[a-zA-Z0-9\\s]+$")
    @ApiModelProperty(notes = "The product's name (unique for each product)")
    private String name;

    @Column(
        name = "description",
        nullable = false,
        length = 525
    )
    @NotNull(message = "Description is required")
    @Size(min = 3, max = 525, message = "Description must be between 3 and 525 characters inclusive")
    @Pattern(message = "Description is not valid (follow this regex: ^[a-zA-Z0-9\\s]+$)", regexp = "^[a-zA-Z0-9\\s]+$")
    @ApiModelProperty(notes = "The product's description")
    private String description; 

    @Column(
        name = "color",
        nullable = false,
        length = 45
    )
    @NotNull(message = "Color is required")
    @Size(min = 3, max = 45, message = "Color must be between 3 and 45 characters inclusive")
    @Pattern(message = "Color is not valid (follow this regex: ^[a-zA-Z]+$)", regexp = "^[a-zA-Z]+$")
    @ApiModelProperty(notes = "The product's color")
    private String color;

    @Column(
        name = "category",
        nullable = false,
        length = 45
    )
    @NotNull(message = "Category is required")
    @Size(min = 3, max = 45, message = "Category must be between 3 and 45 characters inclusive")
    @Pattern(message = "Category is not valid (follow this regex: ^[a-zA-Z]+$)", regexp = "^[a-zA-Z]+$")
    @ApiModelProperty(notes = "The product's category")
    private String category;

    @Column(
        name = "price",
        nullable = false
    )
    @NotNull(message = "Price is required")
    @ApiModelProperty(notes = "The product's price")
    private Double price;


    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List < SupplierHasProduct > suppliers;
    
    @JsonIgnore
    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL, 
        orphanRemoval = true                
    )
    private List < OrderLine > orderLines;
    
    protected Product() {}

    public Product(String name, String description, String color, String category, Double price,
            List<SupplierHasProduct> suppliers, List<OrderLine> orderLines) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.category = category;
        this.price = price;
        this.suppliers = suppliers;
        this.orderLines = orderLines;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<SupplierHasProduct> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierHasProduct> suppliers) {
        this.suppliers = suppliers;
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
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((orderLines == null) ? 0 : orderLines.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((suppliers == null) ? 0 : suppliers.hashCode());
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
        Product other = (Product) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (orderLines == null) {
            if (other.orderLines != null)
                return false;
        } else if (!orderLines.equals(other.orderLines))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (suppliers == null) {
            if (other.suppliers != null)
                return false;
        } else if (!suppliers.equals(other.suppliers))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productId=").append(productId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", price=").append(price);
        sb.append(", suppliers=").append(suppliers);
        sb.append(", orderLines=").append(orderLines);
        sb.append('}');
        return sb.toString();
    }
}
