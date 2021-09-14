package task.orange.assignment.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import task.orange.assignment.model.Supplier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SupplierRepositoryUnitTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeAll
    static void basic() { }

    @BeforeEach
    void setUp() {

        Supplier trueSupplier_1 = new Supplier(
                "one",
                "one",
                "11111",
                "one@gmail.com",
                new ArrayList<>());

        entityManager.persist(trueSupplier_1);
    }

    @Test
    void findById() {

        Supplier trueSupplier_2 = new Supplier(
                "two",
                "two",
                "22222",
                "two@gmail.com",
                new ArrayList<>());

        Long id = entityManager.persist(trueSupplier_2).getSupplierId();

        Supplier s = supplierRepository.findById(id).get();

        assertEquals(id, s.getSupplierId());
    }

    @Test
    void existsByPhoneNumber() {

        String phoneNumber = "11111";

        assertTrue(supplierRepository.existsByPhoneNumber(phoneNumber));
    }

    @Test
    void existsByEmail() {

        String email = "one@gmail.com";

        assertTrue(supplierRepository.existsByEmail(email));
    }

    @Test
    void findByEmail() {

        String email = "one@gmail.com";

        Supplier s = supplierRepository.findByEmail(email);

        assertEquals(email, s.getEmail());
    }

    @AfterEach
    void tearDown() { }
}