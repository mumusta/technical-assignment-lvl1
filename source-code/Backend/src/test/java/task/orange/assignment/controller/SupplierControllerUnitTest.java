package task.orange.assignment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import task.orange.assignment.model.Supplier;
import task.orange.assignment.service.SupplierService;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static task.orange.assignment.UtilTest.AUTHORIZATION_TOKEN;
import static task.orange.assignment.UtilTest.BASE_URL;


@WebMvcTest(SupplierController.class)
class SupplierControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    @BeforeEach
    void setUp() {

        Supplier trueSupplier_1 = new Supplier(
                1L,
                "one",
                "one",
                "11111",
                "one@gmail.com",
                new ArrayList<>());

        Supplier trueSupplier_1_withoutId = new Supplier(
                "one",
                "one",
                "11111",
                "one@gmail.com");

        Mockito.when(supplierService.createNewSupplier(trueSupplier_1_withoutId)).thenReturn(trueSupplier_1);

        Mockito.when(supplierService.getSupplierById(1L)).thenReturn(trueSupplier_1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createNewSupplier() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
                        post(BASE_URL + "/suppliers")
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\n" +
                                        "\t\"firstName\": \"one\",\n" +
                                        "    \"lastName\": \"one\",\n" +
                                        "    \"phoneNumber\": \"11111\",\n" +
                                        "    \"email\": \"one@gmail.com\"\n" +
                                        "}"
                                )
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.supplierId", is(1)))
                .andExpect(jsonPath("$.email", is("one@gmail.com")));
    }

    @Test
    void getAllSuppliers() {
    }

    @Test
    void getSupplierById() throws Exception {

        long id = 1L;

        mockMvc.perform(
                        get(BASE_URL + "/suppliers/" + Long.toString(id))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("one@gmail.com")));
    }

    @Test
    void getAllProductsOfSupplierById() {
    }

    @Test
    void addProductToSupplier() {
    }

    @Test
    void removeProductFromSupplier() {
    }

    @Test
    void updateSupplierById() {
    }

    @Test
    void deleteSupplierById() {
    }
}