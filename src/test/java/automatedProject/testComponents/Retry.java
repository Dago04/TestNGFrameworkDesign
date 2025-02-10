package automatedProject.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * Implementación de {@link IRetryAnalyzer} para reintentar pruebas fallidas en TestNG.
 * Permite configurar cuántas veces se intentará una prueba antes de considerarla fallida.
 */
public class Retry implements IRetryAnalyzer   {
	
	int count  = 0;
	int maxTry = 1;
	

    /**
     * Método que determina si una prueba debe ser reintentada.
     * Se basa en el número de intentos realizados y el número máximo de intentos configurado.
     *
     * @param result Información del test que ha fallado y que se está evaluando para reintentar.
     * @return true si el test debe ser reintentado, false en caso contrario.
     */
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count < maxTry) {
			 count ++;
			 return true;
		}
		return false;
	}

}
