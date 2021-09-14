package task.orange.assignment.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import task.orange.assignment.error.NotFoundException;
import task.orange.assignment.model.Supplier;
import task.orange.assignment.repository.SupplierRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupplierServiceUnitTest {

    @Autowired
    private SupplierService supplierService;

    @MockBean
    private SupplierRepository supplierRepository;


    @BeforeEach
    void setUp() {

        Supplier trueSupplier_1 = new Supplier(
                1L,
                "one",
                "one",
                "11111",
                "one@gmail.com",
                new ArrayList<>());

        Supplier trueSupplier_2 = new Supplier(
                2L,
                "two",
                "two",
                "22222",
                "two@gmail.com",
                new ArrayList<>());

        Mockito.when(supplierRepository.existsById(1L)).thenReturn(true);
        Mockito.when(supplierRepository.existsById(2L)).thenReturn(true);

        Mockito.when(supplierRepository.existsByEmail("one@gmail.com")).thenReturn(true);
        Mockito.when(supplierRepository.existsByEmail("two@gmail.com")).thenReturn(true);

        Mockito.when(supplierRepository.existsByPhoneNumber("11111")).thenReturn(true);
        Mockito.when(supplierRepository.existsByPhoneNumber("22222")).thenReturn(true);

        Mockito.when(supplierRepository.findById(1L)).thenReturn(Optional.of(trueSupplier_1));
        Mockito.when(supplierRepository.findById(2L)).thenReturn(Optional.of(trueSupplier_2));

        Mockito.when(supplierRepository.findByEmail("one@gmail.com")).thenReturn(trueSupplier_1);
        Mockito.when(supplierRepository.findByEmail("two@gmail.com")).thenReturn(trueSupplier_2);

        Mockito.when(supplierRepository.existsById(666L)).thenReturn(false);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProductToSupplier() {
    }

    @Test
    void removeProductFromSupplier() {
    }

    @Test
    void createNewSupplier() {
    }

    @Test
    void updateSupplierById() {
    }

    @Test
    void getSupplierById_validId() {

        Long id = 1L;

        Supplier s = supplierService.getSupplierById(id);

        assertEquals(id, s.getSupplierId());
    }

    @Test
    void getSupplierById_invalidId() {

        Long id = 666L;

        assertThrows(NotFoundException.class, () -> {

            supplierService.getSupplierById(666L);
        });
    }

    @Test
    void getSupplierByEmail() {
    }

    @Test
    void getAllSuppliers() {
    }

    @Test
    void deleteSupplierById() {
    }

    @Test
    void copyImportantMembers() {
    }

    @Test
    void isSupplierExistsById() {
    }

    @Test
    void isSupplierExistsByPhoneNumber() {
    }

    @Test
    void isSupplierExistsByEmail() {
    }
}