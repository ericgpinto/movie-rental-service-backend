package com.technocorp.ericpinto.rentalms.repository;

import com.technocorp.ericpinto.rentalms.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
