package task.orange.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.orange.assignment.model.SupplierHasProduct;
import task.orange.assignment.model.SupplierHasProductKey;

@Repository
public interface SupplierHasProductRepository extends JpaRepository < SupplierHasProduct, SupplierHasProductKey > {
    
}
