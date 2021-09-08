package task.orange.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.orange.assignment.model.Order;

@Repository
public interface OrderRepository extends JpaRepository < Order, Long > {
    
}
