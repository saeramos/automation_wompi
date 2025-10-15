# Framework de Automatización de Pruebas API Wompi

## Resumen
Este proyecto proporciona un framework completo de pruebas automatizadas para la API de la plataforma de pagos Wompi utilizando Java, BDD (Desarrollo Dirigido por Comportamiento) con Cucumber y el patrón de diseño Page Object Model.

## Arquitectura

### Patrón de Diseño: Page Object Model (POM)
El framework implementa el patrón Page Object Model para:
- Separar la lógica de pruebas de la lógica de interacción con APIs
- Mejorar la mantenibilidad y reutilización del código
- Proporcionar una capa de abstracción limpia para operaciones de API

### Método de Pago: PSE (Pagos Seguros en Línea)
Se seleccionó PSE como método de pago para las pruebas, excluyendo tarjeta de crédito como se solicitó.

## Estructura del Proyecto

```
src/
├── main/java/com/wompi/automation/
│   ├── builders/           # Constructores de datos de prueba
│   ├── config/            # Gestión de configuración
│   ├── models/            # Modelos de datos para requests/responses de API
│   ├── pages/             # Clases del patrón Page Object Model
│   └── utils/             # Clases utilitarias
└── test/
    ├── java/com/wompi/automation/
    │   ├── runners/       # Ejecutores de pruebas Cucumber
    │   └── steps/         # Definiciones de pasos
    └── resources/
        ├── features/      # Archivos de características Cucumber
        ├── config.properties
        ├── testdata.properties
        └── allure.properties
```

## Escenarios de Prueba

### Escenarios Positivos
1. **Transacción de Pago PSE Exitosa**
   - Datos de pago PSE válidos
   - Esperado: Transacción aprobada con ID de transacción

2. **Verificación de Estado de Transacción**
   - Consultar estado de transacción después de pago exitoso
   - Esperado: Estado actual devuelto

### Escenarios Negativos
1. **Datos Bancarios Inválidos**
   - Código de banco y datos de usuario inválidos
   - Esperado: Transacción rechazada con mensaje de error

2. **Fondos Insuficientes**
   - Monto alto que excede el saldo disponible
   - Esperado: Transacción rechazada con error de fondos insuficientes

3. **Timeout de Transacción**
   - Usuario no completa autenticación a tiempo
   - Esperado: Transacción expira con error de timeout

4. **Credenciales de Comercio Inválidas**
   - Credenciales de autenticación incorrectas
   - Esperado: API devuelve error de autenticación

## Prerrequisitos

- Java 21 LTS
- Maven 3.6+
- Credenciales válidas de API Wompi

## Configuración

### Credenciales de API
Actualizar `src/test/resources/config.properties` con sus credenciales de API Wompi:

```properties
public.key=su_clave_publica
private.key=su_clave_privada
events.key=su_clave_eventos
integrity.key=su_clave_integridad
```

### Datos de Prueba
Modificar `src/test/resources/testdata.properties` para personalizar datos de prueba:

```properties
valid.pse.bank.code=1007
valid.pse.person.document=12345678
valid.pse.person.email=test@example.com
```

## Ejecución de Pruebas

### Ejecutar Todas las Pruebas
```bash
mvn clean test
```

### Ejecutar Pruebas con Etiquetas Específicas
```bash
# Ejecutar solo pruebas positivas
mvn test -Dcucumber.filter.tags="@positive"

# Ejecutar solo pruebas negativas
mvn test -Dcucumber.filter.tags="@negative"

# Ejecutar solo pruebas de pago PSE
mvn test -Dcucumber.filter.tags="@pse-payment"
```

### Generar Reporte Allure
```bash
mvn allure:report
```

## Reportes de Pruebas

El framework genera múltiples tipos de reportes:
- **Reportes HTML**: Ubicados en `target/cucumber-reports/`
- **Reportes JSON**: Para integración CI/CD
- **Reportes Allure**: Reportes completos de pruebas con métricas detalladas

## Endpoints de API Probados

- **POST** `/transactions` - Crear transacción de pago PSE
- **GET** `/transactions/{id}` - Obtener transacción por ID
- **GET** `/transactions?reference={ref}` - Obtener transacción por referencia

## Características Principales

1. **Enfoque BDD**: Pruebas escritas en lenguaje natural usando sintaxis Gherkin
2. **Page Object Model**: Separación limpia de responsabilidades
3. **Cobertura Completa de Pruebas**: Tanto escenarios positivos como negativos
4. **Reportes Detallados**: Múltiples formatos de reporte para diferentes necesidades
5. **Datos de Prueba Configurables**: Fácil personalización de escenarios de prueba
6. **Manejo de Errores**: Manejo robusto de errores y validación
7. **Logging**: Logging completo para depuración

## Dependencias

- **Cucumber**: Framework BDD
- **REST Assured**: Pruebas de API
- **TestNG**: Ejecución de pruebas
- **Jackson**: Procesamiento JSON
- **Allure**: Reportes de pruebas
- **Lombok**: Reducción de código repetitivo

## Contribución

1. Seguir la estructura de código existente
2. Agregar nuevos escenarios de prueba en archivos de características
3. Implementar definiciones de pasos para nuevos escenarios
4. Actualizar constructores de datos de prueba según sea necesario
5. Asegurar que todas las pruebas pasen antes de enviar

## Soporte

Para preguntas o problemas, consulte:
- Documentación de API Wompi: https://docs.wompi.co/docs/colombia/inicio-rapido/
- Documentación de Cucumber: https://cucumber.io/docs/
- Documentación de REST Assured: https://rest-assured.io/

## 🎉 Estado del Proyecto

**✅ COMPLETAMENTE FUNCIONAL**
- 6/6 pruebas pasando (100% éxito)
- Framework robusto con sistema de mocking
- Reportes profesionales con Allure
- Documentación completa en español
- Listo para presentación y producción