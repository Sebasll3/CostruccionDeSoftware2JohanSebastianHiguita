package domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankProductCatalog {
    private String idBankProduct;
    private String nameBankProduct;
    private BankingCategory cateGory;

}
