package task.orange.assignment.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.orange.assignment.model.AuthMessage;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class BasicAuthController {

    @GetMapping(value = { "/basic_auth", "/basic_auth/" })
    public AuthMessage isOk(){

        return new AuthMessage("Ok");
    }
}
