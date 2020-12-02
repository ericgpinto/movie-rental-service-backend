package com.technocorp.ericpinto.rentms;

import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor

public class UserController {

    private UserService userService;

    @GetMapping(value = "")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        userService.create(user);
        return ResponseEntity.ok(user);
    }
}
