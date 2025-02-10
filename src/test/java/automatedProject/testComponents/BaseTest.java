package automatedProject.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automatedProject.pageObjects.LandingPage;

/**
 * Clase base para todas las pruebas automatizadas.
 * Proporciona la configuración del WebDriver, la inicialización del navegador,
 * la gestión de datos de prueba y la captura de pantallas en caso de errores.
 */
public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	
	 /**
     * Inicializa el WebDriver basado en la configuración definida en el archivo `GlobalData.properties`
     * o en la variable de entorno proporcionada en la ejecución con Maven.
     *
     * @return una instancia de WebDriver configurada según el navegador especificado.
     * @throws IOException si ocurre un error al leer el archivo de configuración.
     */
	public WebDriver initializeDriver() throws IOException {

		  // Cargar propiedades desde el archivo de configuración
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//automatedProject//resources//GlobalData.properties");
		prop.load(file);
		
		// Determinar el navegador a utilizar (prioridad: variable de entorno > archivo de configuración)
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");

		 // Configuración del navegador
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {				
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			 // Configuración para Firefox (pendiente de implementación)
			
		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;

	}

	/**
     * Lee datos de prueba desde un archivo JSON y los convierte en una lista de mapas (HashMap).
     *
     * @param filePath Ruta del archivo JSON a leer.
     * @return una lista de HashMaps, donde cada HashMap representa un conjunto de datos de prueba.
     * @throws IOException si ocurre un error al leer el archivo.
     */
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// READ json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to hashmap // jackson data bind
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;
	}
	
	  /**
     * Captura una captura de pantalla y la guarda en la carpeta de reportes.
     *
     * @param testCaseName Nombre del caso de prueba para nombrar la captura de pantalla.
     * @param driver Instancia del WebDriver en uso.
     * @return Ruta del archivo de la captura de pantalla guardada.
     * @throws IOException si ocurre un error al guardar la imagen.
     */
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	 /**
     * Método ejecutado antes de cada prueba. 
     * Inicializa el WebDriver y navega a la página principal.
     *
     * @return una instancia de {@link LandingPage} para interactuar con la aplicación.
     * @throws IOException si ocurre un error al inicializar el WebDriver.
     */
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();

		landingPage = new LandingPage(driver);

		landingPage.goTo();

		return landingPage;
	}

	/**
     * Método ejecutado después de cada prueba.
     * Cierra la instancia del navegador.
     */
	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.close();
	}
}
