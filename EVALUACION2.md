# EVALUACION 2 - CostruccionDeSoftware2JohanSebastianHiguita

## Informacion general
- Estudiante(s): Integrantes no informados en README.md
- Rama evaluada: main
- Commit evaluado: ceef827dbb8d77644489f67a026acea618807ce4
- Fecha: 2026-04-11

## Tabla de calificacion

| Criterio | Peso | Puntaje (1-5) | Aporte |
|---|---|---|---|
| 1. Modelado de dominio | 20% | 3 | 0.60 |
| 2. Modelado de puertos | 20% | 1 | 0.20 |
| 3. Modelado de servicios de dominio | 20% | 1 | 0.20 |
| 4. Enums y estados | 10% | 3 | 0.30 |
| 5. Reglas de negocio criticas | 10% | 2 | 0.20 |
| 6. Bitacora y trazabilidad | 5% | 3 | 0.15 |
| 7. Estructura interna de dominio | 10% | 2 | 0.20 |
| 8. Calidad tecnica base en domain | 5% | 2 | 0.10 |
| **SUBTOTAL** | | | **1.95** |

## Penalizaciones
- **Nomenclatura mixta con patron espanol (-10%):** Campos como `idIdentification`, `statusUser`, `rolSystem`, enum `SystemRol` con patron de nombre espanol.
- **Estado en String donde debe ser enum (-10%):** `Transfer.transferStatus` es `String` en lugar de enum.

Calculo: 1.95 x 0.90 x 0.90 = **1.58**

## Bonus
- Ninguno.

## Nota final
**1.6 / 5.0**

---

## Hallazgos

### Positivos
- Uso de `BigDecimal` para montos monetarios (correcto).
- Uso de `Lombok` (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`).
- `AuditRecord` con `operationType`, `affectedProductId`, `detailData`.
- `LoanStatus` bien definido: UNDER_STUDY, APPROVED, REJECTED, DISBURSED, IN_ARREARS, CANCELLED (6 estados).
- `AccountStatus` correcto: ACTIVE, BLOCKED, CANCELLED.

### Negativos
- **`Transfer.transferStatus` es `String` en lugar de enum `TransferStatus`.** Penaliza -10%.
- **Nomenclatura con patron espanol:** `SystemRol` (nombre espanol), `idIdentification`, `statusUser`, `rolSystem`, `adress` (typo). Penaliza -10%.
- `AuditRecord.OperationType` usa `String` en lugar de enum.
- **Sin puertos:** no hay interfaces de salida.
- **Sin servicios de dominio:** no hay implementacion de casos de uso.
- README no informa integrantes del proyecto.

## Recomendaciones
1. Cambiar `Transfer.transferStatus: String` a `Transfer.status: TransferStatus` (enum).
2. Renombrar campos al patron ingles: `SystemRole` en lugar de `SystemRol`, `fullName` en lugar de `nombreCompleto` (si existia), etc.
3. Crear puertos por agregado.
4. Implementar servicios de dominio por caso de uso.
5. Identificar integrantes en el README.md.
