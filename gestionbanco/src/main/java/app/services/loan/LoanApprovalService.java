package app.services.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import app.domain.models.Loan;
import app.domain.models.LoanStatus;
import app.ports.LoanPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanApprovalService {
    private final LoanPort loanRepository;

    public void approveLoan(Long loanId, BigDecimal approvedAmount) throws Exception {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new Exception("Préstamo no encontrado"));

        if (!loan.getLoanStatus().equals(LoanStatus.UNDER_STUDY)) {
            throw new Exception("El préstamo no está en estado de evaluación");
        }

        BigDecimal interestRate = new BigDecimal("10");

        loan.setApprovedAmount(approvedAmount);
        loan.setInterestRate(interestRate);
        loan.setLoanStatus(LoanStatus.APPROVED);
        loan.setApprovalDate(LocalDate.now());

        loanRepository.update(loan);
    }
}
