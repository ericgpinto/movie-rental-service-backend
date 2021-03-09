package com.technocorp.ericpinto.rentms.controller;

import com.technocorp.ericpinto.rentms.controller.util.URL;
import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "rentapi/users")
@Api(value = "API REST Users")
@AllArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public User create(@Valid @RequestBody User user){
        return userService.create(user);
    }

    @GetMapping(value = "")
    @ApiOperation("Returns a list of users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping(value = "/email-search")
    @ApiOperation("Returns a user by email")
    public User findByEmail(@RequestParam(value = "email", defaultValue = "") String email){
        email = URL.decodeParam(email);
        return userService.findByEmail(email);
    }

    @GetMapping(value = "/mobile-number-search")
    @ApiOperation("Returns a user by mobile number")
    public User findByMobileNumber(@RequestParam(value = "mobilenumber", defaultValue = "") String mobileNumber){
        mobileNumber = URL.decodeParam(mobileNumber);
        return userService.findByMobileNumber(mobileNumber);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public User findById(@PathVariable String id){
        return userService.findById(id);
    }

    @DeleteMapping(value ="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deletes a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public void delete(@PathVariable String id){
        userService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deletes many users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public void deleteMany(@RequestParam List<String> ids){
        ids.forEach(userService::delete);
    }

    @PutMapping(value ="/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Updates a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public User update(@PathVariable String id, @RequestBody User user){
        return userService.udpated(id, user);
    }

}
