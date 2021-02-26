package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.repository.UserRepository;
import com.technocorp.ericpinto.rentms.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User create(User user){
        return userRepository.insert(user);
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("User não encontrado"));
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User udpated(String id, User user){
        User obj = findById(id);

        obj.setName(user.getName());
        obj.setEmail(user.getEmail());

        return userRepository.save(obj);
    }
}