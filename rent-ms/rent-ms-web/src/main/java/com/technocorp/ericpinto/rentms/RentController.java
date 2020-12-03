package com.technocorp.ericpinto.rentms;

import com.technocorp.ericpinto.rentms.model.Rent;
import com.technocorp.ericpinto.rentms.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Rent> create(@RequestBody Rent rent){
        rentService.create(rent);
        return ResponseEntity.ok(rent);
    }
}
