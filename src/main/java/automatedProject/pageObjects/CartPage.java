package automatedProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automatedProject.abstractComponents.AbstractComponent;



/**
 * Clase CartPage que representa la página del carrito de compras en la aplicación de eCommerce.
 * Extiende AbstractComponent para heredar funcionalidades comunes a todas las páginas.
 */
public class CartPage extends AbstractComponent {

	WebDriver driver;

	/**
     * Constructor de la clase CartPage.
     * 
     * @param driver WebDriver para interactuar con el navegador.
     */
	public CartPage(WebDriver driver) {

		super(driver); // Llama al constructor de la clase padre (AbstractComponent)
		this.driver = driver;
		PageFactory.initElements(driver, this); // Inicializa los elementos web de la página
	}
	
	 // Localizadores de elementos web en la página del carrito
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts; // Lista de productos en el carrito	

	@FindBy(css = ".totalRow button")
	WebElement btnCheckOut;  // Botón para proceder al checkout

	 /**
     * Verifica si un producto específico está presente en el carrito.
     * 
     * @param productName Nombre del producto a buscar en el carrito.
     * @return true si el producto está en el carrito, false en caso contrario.
     */
	public Boolean verifyProductDisplay(String productName) {
		return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	}

	/**
     * Hace clic en el botón de checkout y redirige a la página de pago (CheckOutPage).
     * 
     * @return Instancia de CheckOutPage.
     */
	public CheckOutPage goToCheckOut() {

		btnCheckOut.click();
		
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		
		return checkOutPage;
	}

}
