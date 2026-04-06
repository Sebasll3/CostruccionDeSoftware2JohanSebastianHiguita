package app.services.user;

import org.springframework.stereotype.Service;

import app.domain.models.SystemRol;
import app.domain.models.User;
import app.ports.UserPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserPort userRepository;

    public void assignRole(int userId, SystemRol rol) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        user.setRolSystem(rol);
        userRepository.update(user);
    }
}
