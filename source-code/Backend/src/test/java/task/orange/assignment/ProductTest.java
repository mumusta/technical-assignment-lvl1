package task.orange.assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import task.orange.assignment.AssignmentApplication;
import task.orange.assignment.UtilTest;
import task.orange.assignment.model.Product;
import task.orange.assignment.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;
import static task.orange.assignment.UtilTest.*;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = { AssignmentApplication.class },
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;


    @Test
    void createReadUpdateDeleteProduct() throws Exception {

        Product p = createRandProduct();

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
                        post(BASE_URL + "/products")
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(p))
                )
                .andExpect(status().isCreated());

        long productId = productService.getProductByName(p.getName()).getProductId();

        mockMvc.perform(
                        get(BASE_URL + "/products/" + Long.toString(productId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(p.getName())));

        Product p_ = createRandProduct();

        mockMvc.perform(
                        put(BASE_URL + "/products/" + Long.toString(productId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(p_))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(p_.getName())));

        mockMvc.perform(
                        delete(BASE_URL + "/products/" + Long.toString(productId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void createGetDeleteProduct() throws Exception {

        Product p = createRandProduct();

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
                        post(BASE_URL + "/products")
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(p))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(p.getName())));

        long productId = productService.getProductByName(p.getName()).getProductId();

        mockMvc.perform(
                        get(BASE_URL + "/products/" + Long.toString(productId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(p.getName())));

        mockMvc.perform(
                        delete(BASE_URL + "/products/" + Long.toString(productId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteProduct_withWrongId() throws Exception {

        mockMvc.perform(
                        delete(BASE_URL + "/products/" + Integer.toString(-6))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getProduct_withWrongId() throws Exception {

        mockMvc.perform(
                        get(BASE_URL + "/products/" + Integer.toString(-6))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isNotFound());
    }
}