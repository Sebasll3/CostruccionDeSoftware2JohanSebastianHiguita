package app.services.loan;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import app.domain.models.Loan;
import app.domain.models.LoanStatus;
import app.ports.LoanPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanRequestService {
    private final LoanPort loanRepository;

    public Loan createLoanRequest(String applicantClientId, BigDecimal requestedAmount, Integer termMonths) throws Exception {
        if (requestedAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto solicitado debe ser mayor a cero");
        }

        if (termMonths <= 0 || termMonths > 360) {
            throw new IllegalArgumentException("El plazo debe estar entre 1 y 360 meses");
        }

        Loan loan = new Loan();
        loan.setLoanId(generateLoanId());
        loan.setApplicantClientId(applicantClientId);
        loan.setRequestedAmount(requestedAmount);
        loan.setLoanStatus(LoanStatus.UNDER_STUDY);
        loan.setTermMonths(termMonths);
        loan.setRequestedAmount(requestedAmount);

        loanRepository.save(loan);
        return loan;
    }

    private Long generateLoanId() {
        return System.currentTimeMillis();
    }
}
