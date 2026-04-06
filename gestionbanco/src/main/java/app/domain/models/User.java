package app.domain.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    private int idUser;
    private String relatedEntityId;
    private String fullName;
    private String idIdentification;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String address;
    private SystemRol rolSystem;
    private UserStatus statusUser;
    private String userName;
    private String passwordHash;
}
