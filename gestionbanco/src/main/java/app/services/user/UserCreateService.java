package app.services.user;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import app.domain.models.NaturalPersonClient;
import app.domain.models.SystemRol;
import app.domain.models.User;
import app.domain.models.UserStatus;
import app.ports.UserPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCreateService {
    private final UserPort userRepository;

    public User createUser(String userName, String password, String email, String fullName, SystemRol rol) throws Exception {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario es requerido");
        }

        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido");
        }

        if (userRepository.findByUserName(userName).isPresent()) {
            throw new Exception("El nombre de usuario ya existe");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("El email ya está registrado");
        }

        NaturalPersonClient user = new NaturalPersonClient();
        user.setUserName(userName);
        user.setPasswordHash(password); // En producción, debe hashearse
        user.setEmail(email);
        user.setFullName(fullName);
        user.setStatusUser(UserStatus.ACTIVE);
        user.setRolSystem(rol);
        user.setBirthDate(LocalDate.now().minusYears(18));

        userRepository.save(user);
        return user;
    }
}
