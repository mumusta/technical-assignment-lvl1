package task.orange.assignment;

import org.junit.jupiter.api.Test;
import task.orange.assignment.controller.BasicAuthController;

import static org.junit.jupiter.api.Assertions.*;

class BasicAuthTest {

    @Test
    void shouldIsOk() {

        BasicAuthController basicAuthController = new BasicAuthController();
        assertEquals("Ok", basicAuthController.isOk().getMessage());
    }
}