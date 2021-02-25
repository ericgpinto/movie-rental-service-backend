package com.technocorp.ericpinto.rentms.controller.repository;

import com.technocorp.ericpinto.rentms.controller.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
