package automatedProject.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase responsable de leer datos desde un archivo JSON y convertirlos en una
 * lista de mapas (HashMap). Utiliza la librería Jackson para la conversión de
 * JSON a objetos Java.
 */
public class DataReader {

	/**
	 * Lee un archivo JSON y lo convierte en una lista de mapas (HashMap). Cada mapa
	 * representa un conjunto de datos de prueba parametrizados.
	 *
	 * @return una lista de HashMap, donde cada HashMap contiene un conjunto de
	 *         claves y valores extraídos del JSON.
	 * @throws IOException si ocurre un error al leer el archivo.
	 */
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

		// Leer el archivo JSON y convertirlo en una cadena de texto
		String jsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\automatedProject\\data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Crear un objeto ObjectMapper para manejar la conversión de JSON a Java
		ObjectMapper mapper = new ObjectMapper();

		// Convertir la cadena JSON en una lista de HashMaps
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;

	}
}
