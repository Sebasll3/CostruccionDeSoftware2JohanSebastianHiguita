package app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessClient extends User {
    private String legalName;
    private String tradeName;
    private String companyNIT;
    private String representativeId;
}
