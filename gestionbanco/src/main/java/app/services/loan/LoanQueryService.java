package app.services.loan;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.Loan;
import app.domain.models.LoanStatus;
import app.ports.LoanPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanQueryService {
    private final LoanPort loanRepository;

    public Loan getLoan(Long loanId) throws Exception {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new Exception("Préstamo no encontrado"));
    }

    public List<Loan> getLoansByClient(String clientId) {
        return loanRepository.findByApplicantClientId(clientId);
    }

    public List<Loan> getLoansByStatus(LoanStatus status) {
        return loanRepository.findByStatus(status);
    }
}
