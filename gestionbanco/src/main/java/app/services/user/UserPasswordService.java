package app.services.user;

import org.springframework.stereotype.Service;

import app.domain.models.User;
import app.ports.UserPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPasswordService {
    private final UserPort userRepository;

    public void changePassword(int userId, String oldPassword, String newPassword) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (!user.getPasswordHash().equals(oldPassword)) {
            throw new Exception("La contraseña actual es incorrecta");
        }

        if (newPassword == null || newPassword.length() < 6) {
            throw new IllegalArgumentException("La nueva contraseña debe tener al menos 6 caracteres");
        }

        user.setPasswordHash(newPassword); // En producción, debe hashearse
        userRepository.update(user);
    }
}
