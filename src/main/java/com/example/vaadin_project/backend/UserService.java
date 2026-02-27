package com.example.vaadin_project.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
    }

    public void create(CreateUserDTO createUserDTO){
        User user = new User();
        user.setName(createUserDTO.name());
        user.setLastname(createUserDTO.lastname());
        user.setEmail(createUserDTO.email());
        user.setPassword(createUserDTO.password());
        user.setBirthday(createUserDTO.birthday());

        userRepository.save(user);
    }
    public void modify(Long id, CreateUserDTO dto){
        User user = findById(id);

        user.setName(dto.name());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setBirthday(dto.birthday());
        user.setBirthday(dto.birthday());

        userRepository.save(user);
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
