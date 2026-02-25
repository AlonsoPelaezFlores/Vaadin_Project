package com.example.vaadin_project.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUser {
    private final RepositoryUser repositoryUser;

    public List<User> findAll(){
        return repositoryUser.findAll();
    }

    public User findById(Long id){
        return repositoryUser.findById(id).orElse(null);
    }
    public Long modifyUser(Long id, UserDTO dto){
        User user = repositoryUser.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        user.setName(dto.name());
        user.setLastname(dto.lastname());
        user.setAge(dto.age());
        repositoryUser.saveAndFlush(user);
        return user.getId();
    }
    public void delete(Long id){
        repositoryUser.deleteById(id);
    }
}
