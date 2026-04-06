package app.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.Loan;
import app.domain.models.LoanStatus;
import app.ports.LoanService;
import app.services.loan.LoanApprovalService;
import app.services.loan.LoanDisbursementService;
import app.services.loan.LoanQueryService;
import app.services.loan.LoanRequestService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio de dominio para Loan
 * Delegación de cada función a servicios independientes.
 */
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRequestService loanRequestService;
    private final LoanApprovalService loanApprovalService;
    private final LoanDisbursementService loanDisbursementService;
    private final LoanQueryService loanQueryService;

    @Override
    public Loan createLoanRequest(String applicantClientId, BigDecimal requestedAmount, Integer termMonths) throws Exception {
        return loanRequestService.createLoanRequest(applicantClientId, requestedAmount, termMonths);
    }

    @Override
    public void approveLoan(Long loanId, BigDecimal approvedAmount) throws Exception {
        loanApprovalService.approveLoan(loanId, approvedAmount);
    }

    @Override
    public void disburseLoan(Long loanId, String disbursementAccount) throws Exception {
        loanDisbursementService.disburseLoan(loanId, disbursementAccount);
    }

    @Override
    public Loan getLoan(Long loanId) throws Exception {
        return loanQueryService.getLoan(loanId);
    }

    @Override
    public List<Loan> getLoansByClient(String clientId) {
        return loanQueryService.getLoansByClient(clientId);
    }

    @Override
    public List<Loan> getLoansByStatus(LoanStatus status) {
        return loanQueryService.getLoansByStatus(status);
    }
}
