# 📋 EVALUACIÓN - Sistema Bancario
**Proyecto:** CostruccionDeSoftware2JohanSebastianHiguita
**Estudiante:** Johan Sebastian Higuita
**Rama evaluada:** `main` *(developer existe con el mismo contenido — sin diferencias respecto a main)*
**Fecha de evaluación:** 25/03/2026
**Nota final: 2.1 / 5.0**

---

## 📊 Tabla de Puntajes

| Criterio | Peso | Puntaje (1-5) | Contribución |
|----------|------|----------------|--------------|
| 1. Modelado de dominio | 25% | 2 | 0.50 |
| 2. Relaciones entre entidades | 15% | 2 | 0.30 |
| 3. Uso de enums | 15% | 3 | 0.45 |
| 4. Manejo de estados | 5% | 1 | 0.05 |
| 5. Tipos de datos | 5% | 4 | 0.20 |
| 6. Separación Usuario vs Cliente | 10% | 2 | 0.20 |
| 7. Bitácora | 5% | 3 | 0.15 |
| 8. Reglas básicas de negocio | 5% | 1 | 0.05 |
| 9. Estructura del proyecto | 10% | 2 | 0.20 |
| 10. Repositorio | 10% | 1 | 0.10 |
| **TOTAL BASE** | 100% | | **2.20** |

### Bonus Aplicados

| Bonus | Puntaje |
|-------|---------|
| Herencia parcial (`BankingProduct` abstracto → `BankAccount`, `Loan`; `User` → `BusinessClient`) | +0.10 |
| **Total bonus** | **+0.10** |

### Penalizaciones Aplicadas

| Penalización | Porcentaje |
|---|---|
| Malos nombres de variables/campos (`OperationType`, `RolUser`, `DetailData` con mayúscula; `adress`, `bussName`, `cateGory`, `passwordString`) | -10% |
| **Total penalización** | **-10%** |

**NOTA FINAL: (2.20 + 0.10) × 0.90 = 2.07 → 2.1 / 5.0**

---

## 🔍 Análisis Detallado por Criterio

### 1. Modelado de dominio → 2/5
Entidades implementadas:
- ✅ `User` — clase concreta con atributos básicos del sistema
- ✅ `BusinessClient extends User` — cliente empresa
- ✅ `BankingProduct` (abstract) — base para productos bancarios
- ✅ `BankAccount extends BankingProduct` — cuenta bancaria
- ✅ `Loan extends BankingProduct` — préstamo
- ✅ `Transfer` — transferencia
- ✅ `AuditRecord` — bitácora con Map flexible
- ✅ `BankProductCatalog` — catálogo de productos

**Observaciones:**
- ❌ No existe clase `NaturalPersonClient` para clientes persona natural
- ❌ No existen clases para roles de empleado: `TellerEmployee`, `CommercialEmployee`, `InternalAnalyst`, `CompanySupervisor`, `CompanyOperative`
- ❌ `User` es clase concreta en lugar de abstracta — mezcla todos los conceptos en una sola clase plana
- ⚠️ `SystemRol` tiene 7 roles definidos pero solo `BusinessClient` tiene implementación como clase
- ⚠️ Todos los modelos son completamente anémicos (solo Lombok getters/setters, sin ningún método de negocio)

### 2. Relaciones entre entidades → 2/5
- ✅ `BankAccount extends BankingProduct` — herencia correcta
- ✅ `Loan extends BankingProduct` — herencia correcta
- ✅ `BusinessClient extends User` — herencia correcta
- ✅ `BankingProduct.productType` referencia a `BankProductCatalog`
- ⚠️ `holderId` aparece duplicado: en `BankingProduct` Y en `BankAccount` — redundancia por herencia
- ❌ `User` no tiene `List<BankAccount>`, `List<Loan>`, ni `List<Transfer>` — sin navegabilidad desde el cliente
- ❌ Todas las relaciones entre entidades usan String IDs (`holderId`, `applicantClientId`, `creatorUserId` como Long) en lugar de referencias directas a objetos

### 3. Uso de enums → 3/5
Enums implementados (6 enums):
- ✅ `AccountStatus` — ACTIVE, BLOCKED, CANCELLED
- ✅ `AccountType` — SAVINGS, CURRENT, BUSINESS, PERSONAL
- ✅ `BankingCategory` — LOAN, SAVINGS, ACCOUNTS
- ✅ `Currency` — USD, COP, EUR — ✅ y usada en `BankAccount.currency`
- ✅ `LoanStatus` — UNDER_STUDY, APPROVED, REJECTED, DISBURSED, IN_ARREARS, CANCELLED (6 estados)
- ✅ `SystemRol` — 7 roles: INTERNAL_BANK_ANALYST, COMMERCIAL_EMPLOYEE, INDIVIDUAL_CUSTOMER, COMPANY_EMPLOYEE, CORPORATE_CLIENT, COMPANY_SUPERVISOR, COUNTER_CLERK

**Faltantes:**
- ❌ `TransferStatus` enum — `Transfer.transferStatus` es `String`
- ❌ `UserStatus` enum — `User.statusUser` es `String`
- ⚠️ `AuditRecord.OperationType` es `String` — debería ser un enum de tipo de operación

### 4. Manejo de estados → 1/5
- ❌ `AccountStatus` y `LoanStatus` existen como enums pero ninguna entidad inicializa el estado en el constructor
- ❌ No existe ninguna transición de estado implementada (sin métodos `approve()`, `reject()`, `disburse()`, etc.)
- ❌ `Transfer.transferStatus` es `String` — sin enum ni control de estados
- ❌ `User.statusUser` es `String` — sin enum ni control de estados
- ⚠️ Los enums están definidos pero no se usan para controlar flujos de negocio

### 5. Tipos de datos → 4/5
- ✅ `BankAccount.currentBalance` — `BigDecimal` ✅
- ✅ `Loan.requestedAmount`, `approvedAmount`, `interestRate` — `BigDecimal` ✅
- ✅ `Transfer.amount` — `BigDecimal` ✅
- ✅ `BankAccount.openingDate`, `Loan.approvalDate`, `Loan.disbursementDate` — `LocalDate` ✅
- ✅ `Transfer.creationDate`, `Transfer.approvalDate` — `LocalDateTime` ✅
- ✅ `AuditRecord.OperationDateTime` — `LocalDateTime` ✅
- ✅ `BankAccount.currency` — `Currency` enum ✅
- ⚠️ `User.date` es `String` — debería ser `LocalDate` para la fecha de nacimiento
- ⚠️ `User.statusUser` es `String` — debería ser `UserStatus` enum
- ⚠️ `Transfer.transferStatus` es `String` — debería ser `TransferStatus` enum

Excelente uso de `BigDecimal` y tipos de fecha en los modelos financieros. Principal falla: `User.date` como String.

### 6. Separación Usuario vs Cliente → 2/5
- ✅ `BusinessClient extends User` — cliente empresa como extensión de usuario
- ⚠️ `User` es clase concreta, no abstracta — sirve como base directamente
- ❌ No existe `NaturalPersonClient` para clientes persona natural
- ❌ No hay separación estructural entre "usuario del sistema" (acceso con credenciales) y "cliente del banco" (titular de cuentas/préstamos)
- ❌ `User` mezcla atributos del sistema (`userName`, `passwordString`) con datos del cliente (`idIdentification`, `adress`)
- ❌ Los 7 roles del `SystemRol` enum no tienen representación como clases independientes

### 7. Bitácora → 3/5
- ✅ `AuditRecord` clase de bitácora ✅
- ✅ `DetailData Map<String, Object>` — estructura flexible para datos variables ✅ (mejor práctica)
- ✅ `OperationDateTime LocalDateTime` ✅
- ✅ `userId`, `RolUser`, `AffectedProductId` — contexto de la operación
- ❌ `OperationType` es `String` — debería ser un enum de tipo de operación auditada
- ⚠️ Nombres de campos violan la convención Java (mayúscula inicial): `OperationType`, `OperationDateTime`, `RolUser`, `DetailData`
- ⚠️ `RolUser` es `String` — debería usar `SystemRol` enum
- ⚠️ Sin integración: ninguna clase usa `AuditRecord` para registrar operaciones

### 8. Reglas básicas de negocio → 1/5
- ❌ Ninguna clase tiene métodos de negocio — todos los modelos son completamente anémicos (solo Lombok getters/setters)
- ❌ No existe validación de saldo antes de débito
- ❌ No existe verificación de estado de cuenta antes de operaciones
- ❌ No existe control de quién puede aprobar un préstamo
- ❌ No existe lógica de umbrales para transferencias
- ❌ No existe ninguna regla de negocio bancaria implementada

### 9. Estructura del proyecto → 2/5
```
gestionbanco/src/main/java/
  app/
    BankApplication.java            ← Spring Boot (paquete 'app')
  com/example/gestionbanco/
    GestionbancoApplication.java    ← Spring Boot DUPLICADO
  domain/
    models/                         ← enums y modelos mezclados
```
- ✅ `domain.models` como paquete de modelos
- ❌ Dos clases `@SpringBootApplication` en paquetes diferentes — conflicto de arranque
- ❌ Enums y clases modelo mezclados en el mismo paquete `domain.models` — sin separación
- ⚠️ `domain.models` no es un paquete válido (sin prefijo `com.`, `org.`, etc.)
- ⚠️ `ejemplo.html` en la raíz del repositorio — archivo no relacionado con el proyecto Java

### 10. Repositorio → 1/5
- ⚠️ Nombre tiene TYPO: `CostruccionDeSoftware2JohanSebastianHiguita` (debería ser `Construccion...`)
- ⚠️ README contiene solo: *"Primer Repositorio simulacro de creación"* — sin nombre del estudiante, sin descripción de la materia ni tecnologías
- ❌ 3 commits, ninguno sigue formato ADD/CHG/FIX: `Initial commit`, `cree archivo html de ejemplo`, `first commit`
- ❌ Rama `developer` creada pero con exactamente el mismo contenido que `main` (sin diferencias)
- ❌ Sin tags de versión
- ❌ `ejemplo.html` al nivel de la raíz (archivo obsoleto/de prueba no eliminado)

---

## 📌 Puntos Fuertes
1. **`BigDecimal` correctamente aplicado** en todos los campos monetarios (`BankAccount.currentBalance`, `Loan.requestedAmount/approvedAmount/interestRate`, `Transfer.amount`)
2. **`Currency` enum** definido y usado en `BankAccount.currency`
3. **Tipos de fecha correctos** (`LocalDate`, `LocalDateTime`) en los modelos financieros
4. **`AuditRecord.DetailData`** como `Map<String, Object>` — patrón flexible para datos variables
5. **`BankingProduct` abstracto** como base para `BankAccount` y `Loan` — buen uso de herencia de producto

## ⚠️ Puntos a Mejorar
1. Implementar métodos de negocio en todos los modelos (`deposit()`, `withdraw()`, `approveLoan()`, `executeTransfer()`, etc.) — los modelos anémicos no representan DDD
2. Crear `NaturalPersonClient`, `TellerEmployee`, `CommercialEmployee`, `InternalAnalyst`, `CompanySupervisor` como clases independientes
3. Hacer `User` abstracto
4. Añadir enum `TransferStatus` y `UserStatus`; reemplazar `String` en `Transfer.transferStatus` y `User.statusUser`
5. Inicializar estados en constructores (`loanStatus = UNDER_STUDY`, `accountStatus = ACTIVE`)
6. Corregir convención de nombres Java (minúscula inicial para campos): `operationType`, `operationDateTime`, `rolUser`, `detailData`
7. Corregir typos: `adress` → `address`, `bussName` → `businessName`, `passwordString` → `passwordHash`
8. Eliminar el `GestionbancoApplication.java` duplicado
9. Separar enums en `domain/enums/` y clases en `domain/models/`
10. Mejorar README con nombre del estudiante, materia, tecnologías e instrucciones de ejecución
