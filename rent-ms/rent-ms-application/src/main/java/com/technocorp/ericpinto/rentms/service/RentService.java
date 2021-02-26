package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.Rent;
import com.technocorp.ericpinto.rentms.repository.RentRepository;
import com.technocorp.ericpinto.rentms.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentService {

    private final RentRepository rentRepository;
    private final FilmService filmService;

    public Rent create(Rent rent, Integer idFilm){
        rent.setFilm(filmService.getFilmById(idFilm));
        rent.setInitialDate(LocalDateTime.now());
        rent.setFinalDate(LocalDateTime.now().plusDays(2));
        return rentRepository.insert(rent);
    }

    public List<Rent> findAll(){
        return rentRepository.findAll();
    }

    public Rent findById(String id) {
        return rentRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Locação não encontrada"));
    }

    public void delete(String id){
        findById(id);
        rentRepository.deleteById(id);
    }

    public Rent udpate(String id, Rent rent, Integer idFilm){
        Rent obj = findById(id);
        obj.setUser(rent.getUser());
        obj.setFilm(filmService.getFilmById(idFilm));
        obj.setInitialDate(LocalDateTime.now());
        obj.setFinalDate(LocalDateTime.now().plusDays(2));

        return rentRepository.save(obj);
    }

}
