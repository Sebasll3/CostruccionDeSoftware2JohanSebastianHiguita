package domain.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessClient extends User {
    
    private String companyName;
    private String companyId;
    private String bussName;
    private String nitIdentification;
    private String representativeId;

}