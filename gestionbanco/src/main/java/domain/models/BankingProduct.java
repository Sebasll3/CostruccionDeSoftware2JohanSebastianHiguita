package domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BankingProduct {

    private String productId;
    private BankProductCatalog productType;
    private String holderId;
}