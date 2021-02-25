package com.technocorp.ericpinto.rentms.controller;

import com.technocorp.ericpinto.rentms.controller.service.RentService;
import com.technocorp.ericpinto.rentms.controller.model.Rent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("rentapi/rents")
@Api(value = "API REST Rents")
@AllArgsConstructor

public class RentController {

    private final RentService rentService;

    @GetMapping()
    @ApiOperation("Returns a list of users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public List<Rent> findAll(){
        return rentService.findAll();
    }

    @GetMapping(value ="/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Rent not found")
    })
    public Rent findById(@PathVariable String id){
        return rentService.findById(id);
    }

    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a rent")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public Rent create(@RequestBody Rent rent, @PathVariable Integer id){
        return rentService.create(rent, id);
    }

    @PutMapping(value ="/{id}")
    @ApiOperation("Updates a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "Rent not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public Rent update(@PathVariable String id, @RequestBody Rent rent, @RequestParam Integer idFilm){
        return rentService.udpate(id, rent, idFilm);
    }
    @DeleteMapping(value ="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deletes a rent by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "Rent not found")
    })
    public void delete(@PathVariable String id){
        rentService.delete(id);
    }
}
