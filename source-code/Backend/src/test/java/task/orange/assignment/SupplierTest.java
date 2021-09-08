package task.orange.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import task.orange.assignment.model.Supplier;
import task.orange.assignment.service.SupplierService;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static task.orange.assignment.UtilTest.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = { AssignmentApplication.class },
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class SupplierTest {

    @Autowired
    private MockMvc mockMvcc;

    @Autowired
    private SupplierService supplierService;


    @Test
    void createReadUpdateDeleteSupplier() throws Exception {

        Supplier s = createRandSupplier();

        ObjectMapper mapper = new ObjectMapper();

        mockMvcc.perform(
                        post(BASE_URL + "/suppliers")
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(s))
                )
                .andExpect(status().isCreated());

        long supplierId = supplierService.getSupplierByEmail(s.getEmail()).getSupplierId();

        mockMvcc.perform(
                        get(BASE_URL + "/suppliers/" + Long.toString(supplierId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(s.getEmail())));

        Supplier s_ = createRandSupplier();

        mockMvcc.perform(
                        put(BASE_URL + "/suppliers/" + Long.toString(supplierId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(s_))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(s_.getEmail())));

        mockMvcc.perform(
                        delete(BASE_URL + "/suppliers/" + Long.toString(supplierId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void createGetDeleteSupplier() throws Exception {

        Supplier s = createRandSupplier();

        ObjectMapper mapper = new ObjectMapper();

        mockMvcc.perform(
                        post(BASE_URL + "/suppliers")
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(s))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(s.getEmail())));

        long supplierId = supplierService.getSupplierByEmail(s.getEmail()).getSupplierId();

        mockMvcc.perform(
                        get(BASE_URL + "/suppliers/" + Long.toString(supplierId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(s.getEmail())));

        mockMvcc.perform(
                        delete(BASE_URL + "/suppliers/" + Long.toString(supplierId))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteSupplier_withWrongId() throws Exception {

        mockMvcc.perform(
                        delete(BASE_URL + "/suppliers/" + Integer.toString(-6))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getSupplier_withWrongId() throws Exception {

        mockMvcc.perform(
                        get(BASE_URL + "/suppliers/" + Integer.toString(-6))
                                .header("Authorization", "Basic " + AUTHORIZATION_TOKEN)
                )
                .andExpect(status().isNotFound());
    }
}