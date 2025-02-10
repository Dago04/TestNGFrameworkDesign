package automatedProject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Clase responsable de la configuración y generación de reportes de pruebas
 * automatizadas utilizando ExtentReports y ExtentSparkReporter.
 */
public class ExtentReporterNG {

	/**
	 * Configura y devuelve un objeto de {@link ExtentReports} que se encargará de
	 * generar los reportes de pruebas.
	 *
	 * @return una instancia de {@link ExtentReports} configurada con el nombre del
	 *         reporte y título del documento.
	 */
	public static ExtentReports getReporterObject() {

		// Definir la ruta donde se generará el reporte
		String path = System.getProperty("user.dir") + "\\reports\\index.html";

		// Crear un objeto ExtentSparkReporter con la ruta del reporte
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		// Configurar el nombre del reporte y el título del documento
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		// Crear un objeto ExtentReports y adjuntar el reporter configurado
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);

		// Agregar información adicional sobre el sistema o el tester
		extent.setSystemInfo("Tester", "Dago");

		return extent;
	}
}
