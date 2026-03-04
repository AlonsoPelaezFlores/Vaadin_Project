package com.example.vaadin_project.backend.service;

import com.example.vaadin_project.backend.repo.UserRepository;
import com.example.vaadin_project.backend.dto.ChangePasswordDTO;
import com.example.vaadin_project.backend.dto.CreateUserDTO;
import com.example.vaadin_project.backend.dto.UserProfileDTO;
import com.example.vaadin_project.backend.entity.User;
import com.example.vaadin_project.backend.entity.UserPrincipal;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileDTO getCurrentUserDTO(){
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = findById(principal.getId());
        return new UserProfileDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getBirthday(),
                user.getRole()
        );
    }
    public List<User> findAll(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userPrincipal.getUsername();
        return userRepository.findAllByEmailNot(email);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not Found"));
    }

    public void create(CreateUserDTO createUserDTO){
        User user = new User();
        user.setName(createUserDTO.getName());
        user.setLastname(createUserDTO.getLastname());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setBirthday(createUserDTO.getBirthday());
        user.setRole(createUserDTO.getRole());

        userRepository.save(user);
    }
    public void modifyPersonalInformation(UserProfileDTO profileDTO){
        User user = findById(profileDTO.getId());

        user.setEmail(profileDTO.getEmail());
        user.setBirthday(profileDTO.getBirthday());

        userRepository.save(user);
    }
    public void modifyUserInformation(UserProfileDTO profileDTO){
        User user = findById(profileDTO.getId());

        user.setName(profileDTO.getName());
        user.setLastname(profileDTO.getLastname());
        user.setEmail(profileDTO.getEmail());
        user.setBirthday(profileDTO.getBirthday());
        user.setRole(profileDTO.getRole());

        userRepository.save(user);
    }
    public boolean changePassword(Long userId, ChangePasswordDTO passwordDTO){

        User user = findById(userId);
        String password = passwordDTO.password();
        String newPassword = passwordDTO.newPassword();
        String confirmPassword = passwordDTO.confirmPassword();

        if (!passwordEncoder.matches(password, user.getPassword())){
            Notification.show("Contraseña incorrecta");
            return false;
        }
        if (!newPassword.equals(confirmPassword)){
            Notification.show("La nueva contraseña no coinciden");
            return false;
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
