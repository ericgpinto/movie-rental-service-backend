package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.Rent;
import com.technocorp.ericpinto.rentms.repository.RentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RentService {

    private RentRepository rentRepository;

    public Rent create(Rent rent){
        return rentRepository.insert(rent);
    }

    public List<Rent> findAll(){
        return rentRepository.findAll();
    }

}
