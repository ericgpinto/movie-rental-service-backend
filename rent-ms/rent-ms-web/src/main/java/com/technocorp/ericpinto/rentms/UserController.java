package com.technocorp.ericpinto.rentms;

import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "rentapi/users")
@Api(value = "API REST Users")
@AllArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping(value = "")
    @ApiOperation("Returns a list of users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public User create(@RequestBody User user){
        User objUser = userService.create(user);
        return objUser;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Returns a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> findById(@PathVariable String id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value ="/{id}")
    @ApiOperation("Deletes a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ApiOperation("Deletes many users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<Void> deleteMany(@RequestParam List<String> ids){
        ids.stream().forEach(x -> userService.delete(x));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value ="/{id}")
    @ApiOperation("Updates a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public ResponseEntity<Void> update(@RequestBody User user, @PathVariable String id){
        User obj = userService.fromUser(user);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();
    }
}
