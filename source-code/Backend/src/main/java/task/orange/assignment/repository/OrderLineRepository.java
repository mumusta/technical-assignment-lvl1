package task.orange.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.orange.assignment.model.OrderLine;
import task.orange.assignment.model.OrderLineKey;

@Repository
public interface OrderLineRepository extends JpaRepository < OrderLine, OrderLineKey > {
    
}
