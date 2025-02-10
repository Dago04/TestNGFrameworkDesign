package automatedProject.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automatedProject.resources.ExtentReporterNG;

/**
 * Implementación de {@link ITestListener} para manejar eventos de prueba en TestNG.
 * Esta clase se encarga de la reportería y captura de pantallas en caso de fallos.
 */
public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	/**
     * Método que se ejecuta al inicio de cada prueba.
     * Inicializa un nuevo test en el reporte de ExtentReports.
     *
     * @param result Información del test que está iniciando.
     */
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique thread id -> test
	}
	 /**
     * Método que se ejecuta cuando una prueba se ejecuta con éxito.
     * Registra el estado de éxito en el reporte.
     *
     * @param result Información del test que ha finalizado con éxito.
     */
	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	/**
     * Método que se ejecuta cuando una prueba falla.
     * Captura el error, toma una captura de pantalla y la adjunta al reporte.
     *
     * @param result Información del test que ha fallado.
     */
	@Override
	public void onTestFailure(ITestResult result) {
		
		 // Registra el error en el reporte
		extentTest.get().fail(result.getThrowable());
		
		try {
			 // Obtiene el driver de la instancia del test
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String Filepath = null;
		try {
			Filepath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 // Adjunta la captura de pantalla al reporte
		extentTest.get().addScreenCaptureFromPath(Filepath,result.getMethod().getMethodName());
	}
	 /**
     * Método que se ejecuta al finalizar la ejecución de todas las pruebas dentro de un contexto.
     * Genera y guarda el reporte final de ExtentReports.
     *
     * @param context Información del contexto de ejecución.
     */
	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
	}
	
}
