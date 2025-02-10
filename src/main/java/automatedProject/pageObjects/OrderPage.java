package automatedProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automatedProject.abstractComponents.AbstractComponent;


/**
 * Clase OrderPage que representa la página de pedidos en la aplicación de eCommerce.
 * Extiende AbstractComponent para reutilizar funcionalidades comunes.
 */
public class OrderPage extends AbstractComponent {

	WebDriver driver;

	/**
     * Constructor de la clase OrderPage.
     * 
     * @param driver WebDriver para interactuar con el navegador.
     */
	public OrderPage(WebDriver driver) {

		super(driver); // Llama al constructor de la clase padre (AbstractComponent)
		this.driver = driver;
		PageFactory.initElements(driver, this); // Inicializa los elementos web de la página
	}
	
	 // Localizadores de elementos
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames; // Lista de nombres de productos en la tabla de pedidos

	@FindBy(css = ".totalRow button")
	WebElement btnCheckOut;  // Botón para proceder al checkout (si aplica)

	 /**
     * Verifica si un producto específico está presente en la lista de pedidos.
     * 
     * @param productName Nombre del producto a buscar.
     * @return true si el producto está en la lista, false en caso contrario.
     */
	public Boolean verifyOrderDisplay(String productName) {
		return productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	}

}
