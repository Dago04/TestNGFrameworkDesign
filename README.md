# Framework de Pruebas Automatizadas con TestNG y Selenium

Este repositorio contiene un **framework de pruebas automatizadas** diseñado para aplicaciones web, utilizando **TestNG** como framework de pruebas y **Selenium WebDriver** para la automatización de interacciones con el navegador. El framework está diseñado para ser extensible, escalable y fácil de configurar para diferentes entornos de prueba.

## Características

- **Automatización Web**: Utiliza Selenium WebDriver para interactuar con el navegador.
- **Pruebas Parametrizadas**: Permite ejecutar pruebas con múltiples conjuntos de datos usando archivos JSON.
- **Reportes Extensivos**: Integración con **ExtentReports** para generar reportes detallados de las pruebas.
- **Reintentos de Pruebas**: Implementación de reintentos para pruebas fallidas con la clase `Retry`.
- **Manejo de Capturas de Pantalla**: Captura automáticamente pantallas en caso de fallos en las pruebas.
- **Compatibilidad con Navegadores**: Compatible con múltiples navegadores como Chrome y Edge.
- **Estrategia de Page Object Model (POM)**: Uso de POM para separar la lógica de negocio de la interfaz de usuario.
- **Ejecución en Headless Mode**: Posibilidad de ejecutar pruebas en modo headless para entornos CI/CD.

## Estructura del Proyecto

- **`src/main/java`**: Contiene las clases de implementación del framework.
  - **`abstractComponent`**: Clases abstractas comunes para el framework.
  - **`pageObject`**: Contiene las clases que representan las páginas de la aplicación (POM).
  - **`resources`**: Archivos de configuración y recursos adicionales como `GlobalData.properties`.

- **`src/test/java`**: Contiene las clases de pruebas y componentes adicionales del framework.
    - **`testComponents`**: Contiene clases de soporte como `BaseTest`, `Listeners`, `Retry` y utilidades adicionales.
    - **`data`**: Archivos de datos JSON utilizados para las pruebas parametrizadas.
    - **`tests`**: Contiene las clases de pruebas que se ejecutan.

- **`reports`**: Directorio donde se almacenan los reportes generados por ExtentReports.

## Requisitos Previos

Antes de ejecutar el framework, asegúrate de tener los siguientes requisitos:

- **Java 8 o superior**
- **Maven**
- **Dependencias de Selenium y TestNG** (se gestionan automáticamente con Maven)
- **WebDriver para los navegadores de tu elección** (Chrome, Edge, Firefox)

## Configuración

1. Clona este repositorio en tu máquina local:

    ```bash
    git clone https://github.com/tu_usuario/tu_repositorio.git
    ```

2. Abre el proyecto en tu IDE favorito (por ejemplo, IntelliJ IDEA o Eclipse).

3. Asegúrate de que tu `pom.xml` tenga las dependencias correctas para **Selenium**, **TestNG** y **ExtentReports**.

4. Configura el archivo `GlobalData.properties` para incluir tu navegador preferido:

    ```properties
    browser=chrome
    ```

    Puedes pasar el navegador por consola de Maven utilizando:

    ```bash
    mvn test -Dbrowser=chrome
    ```

5. Si deseas ejecutar las pruebas en modo headless, agrega el parámetro adecuado:

    ```properties
    browser=chromeheadless
    ```
6. Si usas un IDE como IntelliJ o Eclipse:

  En IntelliJ IDEA:
    ```Ve a la pestaña Maven (View > Tool Windows > Maven).
    Haz clic en el ícono de Actualizar Proyecto (parece dos flechas en círculo) o usa Ctrl + Shift + O.
    ```
 
  En Eclipse:
   ```
   Haz clic derecho en el proyecto.
   Ve a Maven > Update Project (Alt + F5).
   ```
## Ejecución de Pruebas

Para ejecutar las pruebas, usa el siguiente comando Maven:

```bash
mvn test
