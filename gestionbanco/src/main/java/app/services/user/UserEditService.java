package app.services.user;

import org.springframework.stereotype.Service;

import app.domain.models.User;
import app.ports.UserPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEditService {
    private final UserPort userRepository;

    public void editUser(int userId, String fullName, String email, String phone, String address) throws Exception {
        User existing = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido");
        }

        existing.setFullName(fullName);
        existing.setEmail(email);
        existing.setPhone(phone);
        existing.setAddress(address);
        userRepository.update(existing);
    }
}
