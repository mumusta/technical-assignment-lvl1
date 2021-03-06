package task.orange.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.orange.assignment.model.Product;

@Repository
public interface ProductRepository extends JpaRepository < Product, Long > {

    public Product findByName(String name);
    public boolean existsByName(String name);
}
