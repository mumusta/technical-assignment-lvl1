package task.orange.assignment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "supplier_tbl")
@ApiModel(description = "Supplier model represents the properties and the behaviour of a supplier.\n" +
        "Note that supplierId, phoneNumber, and email are unique for each supplier, and supplierId is generated automatically.")
public class Supplier implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
        name = "supplier_id",
        updatable = false,
        nullable = false
    )
    @ApiModelProperty(notes = "The unique id of the supplier (generated automatically)")
    private Long supplierId;
    
    @Column(
        name = "first_name",
        nullable = false,
        length = 45
    )
    @NotNull(message = "First Name is required")
    @Size(min = 3, max = 45, message = "First Name must be between 3 and 45 characters inclusive")
    @Pattern(message = "First Name is not valid (follow this regex: ^[a-zA-Z]+$)", regexp = "^[a-zA-Z]+$")
    @ApiModelProperty(notes = "Supplier's first name")
    private String firstName; 

    @Column(
        name = "last_name",
        nullable = false,
        length = 45
    )
    @NotNull(message = "Last Name is required")
    @Size(min = 3, max = 45, message = "Last Name must be between 3 and 45 characters inclusive")
    @Pattern(message = "Last Name is not valid (follow this regex: ^[a-zA-Z]+$)", regexp = "^[a-zA-Z]+$")
    @ApiModelProperty(notes = "Supplier's last name")
    private String lastName; 

    @Column(
        name = "phone_number",
        nullable = false,
        length = 25,
        unique = true
    )
    @NotNull(message = "Phone Number is required")
    @Size(min = 5, max = 25, message = "Phone Number must be between 5 and 25 characters inclusive")
    @Pattern(message = "Phone Number is not valid (follow this regex: [0-9]+)", regexp = "[0-9]+")
    @ApiModelProperty(notes = "Supplier's phone number (unique for each supplier)")
    private String phoneNumber;

    @Column(
        name = "email",
        nullable = false,
        length = 265,
        unique = true
    )
    @NotNull(message = "Email is required")
    @Size(min = 5, max = 265, message = "Email must be between 5 and 265 characters inclusive")
    @Email(message = "Email is not valid",
           regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @ApiModelProperty(notes = "Supplier's email (unique for each supplier)")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List < SupplierHasProduct > ownedProducts;


    
    protected Supplier() {}

    public Supplier(String firstName, String lastName, String phoneNumber, String email,
            List<SupplierHasProduct> ownedProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ownedProducts = ownedProducts;
    }


    public List< Product > getProducts() {
        
        List < Product > result = new ArrayList<>(); 
        
        for (SupplierHasProduct x: ownedProducts)
            result.add(x.getProduct());

        return result;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SupplierHasProduct> getOwnedProducts() {
        return ownedProducts;
    }

    public void setOwnedProducts(List<SupplierHasProduct> ownedProducts) {
        this.ownedProducts = ownedProducts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((ownedProducts == null) ? 0 : ownedProducts.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((supplierId == null) ? 0 : supplierId.hashCode());
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
        Supplier other = (Supplier) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (ownedProducts == null) {
            if (other.ownedProducts != null)
                return false;
        } else if (!ownedProducts.equals(other.ownedProducts))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (supplierId == null) {
            if (other.supplierId != null)
                return false;
        } else if (!supplierId.equals(other.supplierId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Supplier{");
        sb.append("supplierId=").append(supplierId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", ownedProducts=").append(ownedProducts);
        sb.append('}');
        return sb.toString();
    }
}
