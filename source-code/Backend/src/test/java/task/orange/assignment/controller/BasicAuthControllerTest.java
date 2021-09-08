package task.orange.assignment.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAuthControllerTest {

    @Test
    void shouldIsOk() {

        BasicAuthController basicAuthController = new BasicAuthController();
        assertEquals("Ok", basicAuthController.isOk().getMessage());
    }
}