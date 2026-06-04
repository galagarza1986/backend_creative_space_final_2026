package ucr.ac.cr.creativespace_final.service;

import org.springframework.stereotype.Service;
import ucr.ac.cr.creativespace_final.model.DTOs.LoginRequestDTO;
import ucr.ac.cr.creativespace_final.model.DTOs.UserRequestDTO;
import ucr.ac.cr.creativespace_final.model.DTOs.UserResponseDTO;
import ucr.ac.cr.creativespace_final.model.UserEntity;
import ucr.ac.cr.creativespace_final.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserResponseDTO> responseList = new ArrayList<>();

        for (UserEntity user : users) {
            responseList.add(convertToResponseDTO(user));
        }

        return responseList;
    }

    public UserResponseDTO getUserById(Integer id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return convertToResponseDTO(optionalUser.get());
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());

        UserEntity savedUser = userRepository.save(user);

        return convertToResponseDTO(savedUser);
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO dto) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        UserEntity user = optionalUser.get();

        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());

        UserEntity updatedUser = userRepository.save(user);

        return convertToResponseDTO(updatedUser);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }

    public UserResponseDTO login(LoginRequestDTO dto) {
        Optional<UserEntity> optionalUser =
                userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }

        return convertToResponseDTO(optionalUser.get());
    }

    private UserResponseDTO convertToResponseDTO(UserEntity user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole()
        );
    }

    public UserEntity getUserEntityByEmail(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return optionalUser.get();
    }
}
