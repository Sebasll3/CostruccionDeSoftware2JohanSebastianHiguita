package domain.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int idUser;
    private String idRelation;
    private String fullName;
    private String idIdentification;
    private String email;
    private String phone;
    private String date;
    private String adress;
    private SystemRol rolSystem;
    private String statusUser;
    private String userName;
    private String passwordString;

}
