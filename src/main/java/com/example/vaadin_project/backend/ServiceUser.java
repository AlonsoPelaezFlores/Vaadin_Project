package com.example.vaadin_project.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUser {
    private final RepositoryUser repositoryUser;

    public List<User> findAll(){
        return repositoryUser.findAll();
    }

    public User findById(Long id){
        return repositoryUser.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
    }

    public void create(UserDTO userDTO){
        User newUser = new User();
        newUser.setName(userDTO.name());
        newUser.setLastname(userDTO.lastname());
        newUser.setAge(userDTO.age());

        repositoryUser.save(newUser);
    }
    public void modify(Long id, UserDTO dto){
        User user = repositoryUser.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        user.setName(dto.name());
        user.setLastname(dto.lastname());
        user.setAge(dto.age());
        repositoryUser.save(user);
    }
    public void delete(Long id){
        repositoryUser.deleteById(id);
    }
}
