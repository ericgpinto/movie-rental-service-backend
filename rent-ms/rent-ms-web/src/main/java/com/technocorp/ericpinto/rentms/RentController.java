package com.technocorp.ericpinto.rentms;

import com.technocorp.ericpinto.rentms.model.Rent;
import com.technocorp.ericpinto.rentms.service.RentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
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
    @ApiOperation("Returns a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Rent not found")
    })
    public ResponseEntity<Rent> findById(@PathVariable String id){
        Rent rent = rentService.findById(id);
        return ResponseEntity.ok(rent);
    }

    @PostMapping(value = "/{id}")
    @ApiOperation("Creates a rent")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns create"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public ResponseEntity<Rent> create(@RequestBody Rent rent, @PathVariable Integer id){
        rentService.create(rent, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rent.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value ="/{id}")
    @ApiOperation("Updates a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "Rent not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public ResponseEntity<Rent> update(@RequestBody Rent rent, @PathVariable String id, @RequestParam Integer idFilm){
        rentService.udpate(id, rent, idFilm);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value ="/{id}")
    @ApiOperation("Deletes a rent by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "Rent not found")
    })
    public ResponseEntity<Void> delete(@PathVariable String id){
        rentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
