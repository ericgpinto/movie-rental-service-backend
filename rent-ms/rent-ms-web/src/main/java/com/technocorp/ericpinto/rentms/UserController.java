package com.technocorp.ericpinto.rentms;

import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        User objUser = userService.create(user);
        return objUser;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMany(@RequestParam List<String> ids){
        ids.stream().forEach(x -> userService.delete(x));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Void> update(@RequestBody User user, @PathVariable String id){
        User obj = userService.fromUser(user);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();
    }
}
