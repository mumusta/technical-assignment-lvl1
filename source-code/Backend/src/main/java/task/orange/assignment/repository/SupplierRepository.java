package task.orange.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.orange.assignment.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository < Supplier, Long > {
    
    public boolean existsByPhoneNumber(String phoneNumber);
    public boolean existsByEmail(String email);
}
