package app.services.user;

import org.springframework.stereotype.Service;

import app.domain.models.User;
import app.domain.models.UserStatus;
import app.ports.UserPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthenticateService {
    private final UserPort userRepository;

    public User authenticate(String userName, String password) throws Exception {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (!user.getPasswordHash().equals(password)) {
            throw new Exception("Contraseña incorrecta");
        }

        if (user.getStatusUser() != UserStatus.ACTIVE) {
            throw new Exception("El usuario no está activo");
        }

        return user;
    }
}
