package task.orange.assignment.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import task.orange.assignment.AssignmentApplication;
import task.orange.assignment.model.Product;
import task.orange.assignment.service.ProductService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = { AssignmentApplication.class },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc*/
class ProductControllerUnitTest {
/*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    static final private String AUTHORIZATION_TOKEN  = "YWRtaW46MTIz";
    static final private String BASE_URL = "/api/v1/products";

    @Test
    void createNewProduct() {
    }

    @Test
    void getAllProducts_hitWithAuth() throws Exception {

        given(productService.getAllProducts(anyInt(), anyInt())).willReturn(null);

        mockMvc.perform(get(BASE_URL).header("Authorization", "Basic " + AUTHORIZATION_TOKEN))
                .andExpect(status().isOk());
    }

    @Test
    void getAllProducts_hitWithoutAuth() throws Exception {

        given(productService.getAllProducts(anyInt(), anyInt())).willReturn(null);

        mockMvc.perform(get(BASE_URL ))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getProductById_hitWithoutAuth() throws Exception{

        given(productService.getAllProducts(anyInt(), anyInt())).willReturn(null);

        mockMvc.perform(get(BASE_URL  ))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAllSuppliersOfProductById_hitWithoutAuth() throws Exception{

        given(productService.getAllProducts(anyInt(), anyInt())).willReturn(null);

        mockMvc.perform(get(BASE_URL  ))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updateProductById_hitWithoutAuth() throws Exception{

        given(productService.getAllProducts(anyInt(), anyInt())).willReturn(null);

        mockMvc.perform(get(BASE_URL  ))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteProductById_hitWithoutAuth() throws Exception{

        given(productService.deleteProductById(anyLong())).willReturn(anyBoolean());

        mockMvc.perform(delete(BASE_URL + "/"))
                .andExpect(status().isUnauthorized());
    }*/
}