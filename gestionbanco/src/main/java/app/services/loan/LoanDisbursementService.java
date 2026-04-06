package app.services.loan;

import org.springframework.stereotype.Service;

import app.domain.models.Loan;
import app.domain.models.LoanStatus;
import app.ports.BankAccountService;
import app.ports.LoanPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanDisbursementService {
    private final LoanPort loanRepository;
    private final BankAccountService bankAccountService;

    public void disburseLoan(Long loanId, String disbursementAccount) throws Exception {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new Exception("Préstamo no encontrado"));

        if (!loan.getLoanStatus().equals(LoanStatus.APPROVED)) {
            throw new Exception("Solo se pueden desembolsar préstamos aprobados");
        }

        bankAccountService.getAccount(disbursementAccount);
        bankAccountService.deposit(disbursementAccount, loan.getApprovedAmount());

        loan.setDisbursementAccount(disbursementAccount);
        loan.setDisbursementDate(java.time.LocalDate.now());
        loan.setLoanStatus(LoanStatus.DISBURSED);

        loanRepository.update(loan);
    }
}
