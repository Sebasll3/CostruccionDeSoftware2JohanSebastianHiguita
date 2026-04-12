# EVALUACION 2 - CostruccionDeSoftware2JohanSebastianHiguita

## Informacion general
- Estudiante(s): Johan Sebastian Higuita (usuario GitHub: Sebasll3)
- Rama evaluada: main
- Commit evaluado: d6cdac745b3e042ef6d0573c958899236764c386
- Fecha: 2026-04-11

## Tabla de calificacion

| Criterio | Peso | Puntaje (1-5) | Aporte |
|---|---|---|---|
| 1. Modelado de dominio | 20% | 4 | 0.80 |
| 2. Modelado de puertos | 20% | 3 | 0.60 |
| 3. Modelado de servicios de dominio | 20% | 3 | 0.60 |
| 4. Enums y estados | 10% | 4 | 0.40 |
| 5. Reglas de negocio criticas | 10% | 4 | 0.40 |
| 6. Bitacora y trazabilidad | 5% | 5 | 0.25 |
| 7. Estructura interna de dominio | 10% | 4 | 0.40 |
| 8. Calidad tecnica base en domain | 5% | 3 | 0.15 |
| **SUBTOTAL** | | | **3.60** |

## Penalizaciones
- **Nomenclatura deficiente (-5%):** Typos en campos de dominio: `adress` (debe ser `address`) en `User`, `bussName` (debe ser `businessName`) en `BusinessClient`. Inconsistencia menor de calidad.

Calculo: 3.60 x 0.95 = **3.42**

## Bonus
- +0.2: 9 puertos bien estructurados separando entrada (service interfaces) de salida (repository ports).
- +0.1: Excelente trazabilidad con `AuditRecord`, `AuditPort` y `AuditServiceImpl` con metodos semanticos.

Total bonus: +0.3

## Nota final
**3.7 / 5.0**

---

## Hallazgos

### Positivos
- **Arquitectura hexagonal bien implementada** con separacion clara de puertos de entrada y salida.
- **8 entidades:** `User`, `BankAccount`, `Transfer`, `Loan`, `BusinessClient`, `AuditRecord`, `BankingProduct`, `BankProductCatalog`.
- **9 puertos:** 4 service interfaces (`BankAccountService`, `LoanService`, `TransferService`, `UserService`) + 5 repository ports (`BankAccountPort`, `LoanPort`, `TransferPort`, `UserPort`, `AuditPort`).
- **5 implementaciones de servicio:** `BankAccountServiceImpl`, `LoanServiceImpl`, `TransferServiceImpl`, `UserServiceImpl`, `AuditServiceImpl` con sub-servicios especializados.
- **BigDecimal** para todos los montos (correcto para precision financiera).
- **7 enums** bien definidos: `AccountType`, `AccountStatus`, `Currency`, `LoanStatus` (6 estados: UNDER_STUDY, APPROVED, REJECTED, DISBURSED, IN_ARREARS, CANCELLED), `SystemRol`, `BankingCategory`, `OperationType`.
- `AuditRecord` con `AuditPort` y `AuditServiceImpl.logDeposit()`, `logWithdraw()`, `logTransfer()`, `logLoanRequest()`, `logUserOperation()`.
- Lombok y `@Service` no se penalizan en esta evaluacion.

### Negativos
- Typos en campos: `adress` en User, `bussName` en BusinessClient. Penaliza -5% nomenclatura.
- Manejo de excepciones con `throws Exception` generico en firmas de puertos (debe ser excepciones de dominio especificas).
- Puertos y servicios estan en `app/` no en `domain/`, lo cual es aceptable pero no es dominio puro.

## Recomendaciones
1. Corregir typos: `adress` -> `address`, `bussName` -> `businessName`.
2. Definir `DomainException` o excepciones especificas en lugar de `throws Exception`.
3. Excelente trabajo de arquitectura hexagonal con puertos y servicios completos.
