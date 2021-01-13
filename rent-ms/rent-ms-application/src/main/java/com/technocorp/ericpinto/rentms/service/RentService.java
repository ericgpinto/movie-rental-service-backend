package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.Rent;
import com.technocorp.ericpinto.rentms.repository.RentRepository;
import com.technocorp.ericpinto.rentms.service.exceptions.ObjectNotFoundException;
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

    public Rent findById(String id) {
        return rentRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Locação não encontrada"));
    }

    public Rent update(Rent obj){
        Rent updatedRent = findById(obj.getId());
        updateData(updatedRent, obj);
        return rentRepository.save(updatedRent);
    }

    private void updateData(Rent updatedUser, Rent obj) {
        updatedUser.setUser(obj.getUser());
        updatedUser.setUrlFilm(obj.getUrlFilm());
        updatedUser.setInitialDate(obj.getInitialDate());
        updatedUser.setFinalDate(obj.getFinalDate());
    }

    public void delete(String id){
        findById(id);
        rentRepository.deleteById(id);
    }

}
