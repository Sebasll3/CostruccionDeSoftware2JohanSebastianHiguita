package app.services.user;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.SystemRol;
import app.domain.models.User;
import app.ports.UserPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserPort userRepository;

    public User getUserById(int userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
    }

    public User getUserByUserName(String userName) throws Exception {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
    }

    public List<User> getUsersByRole(SystemRol rol) {
        return userRepository.findByRole(rol);
    }

    public User getUserByIdentification(String identification) throws Exception {
        return userRepository.findByIdentification(identification)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
    }
}
