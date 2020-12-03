package com.technocorp.ericpinto.rentms.repository;

import com.technocorp.ericpinto.rentms.model.Rent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends MongoRepository<Rent, String> {
}
