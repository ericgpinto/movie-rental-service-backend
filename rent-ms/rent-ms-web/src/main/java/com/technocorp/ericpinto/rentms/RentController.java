package com.technocorp.ericpinto.rentms;

import com.technocorp.ericpinto.rentms.model.Rent;
import com.technocorp.ericpinto.rentms.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rents")
@AllArgsConstructor

public class RentController {

    private RentService rentService;

    @GetMapping(value = "")
    public List<Rent> findAll(){
        return rentService.findAll();
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Rent> findById(@PathVariable String id){
        Rent rent = rentService.findById(id);
        return ResponseEntity.ok(rent);
    }

    @PostMapping
    public ResponseEntity<Rent> create(@RequestBody Rent rent){
        rentService.create(rent);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rent.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Void> update(@RequestBody Rent rent, @PathVariable String id){
        rent.setId(id);
        rentService.update(rent);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        rentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
