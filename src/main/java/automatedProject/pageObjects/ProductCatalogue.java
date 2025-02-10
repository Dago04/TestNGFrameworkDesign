package automatedProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automatedProject.abstractComponents.AbstractComponent;

/**
 * Clase ProductCatalogue que representa la página del catálogo de productos en
 * la aplicación de eCommerce. Extiende AbstractComponent para reutilizar
 * funcionalidades comunes.
 */
public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	/**
	 * Constructor de la clase ProductCatalogue.
	 * 
	 * @param driver WebDriver para interactuar con el navegador.
	 */
	public ProductCatalogue(WebDriver driver) {

		super(driver); // enviar el driver al padre mediante super
		this.driver = driver;
		PageFactory.initElements(driver, this); // inicia los selectores
	}

	// Localizadores de elementos

	@FindBy(css = ".mb-3")
	List<WebElement> products; // Lista de productos disponibles en el catálogo

	@FindBy(css = ".ng-animating")
	WebElement spinner; // Indicador de carga de la página

	By productsBy = By.cssSelector(".mb-3"); // Selector para los productos
	By addToCart = By.cssSelector(".card-body button:last-of-type"); // Botón para agregar al carrito
	By toastMessage = By.cssSelector("#toast-container"); // Mensaje de confirmación tras agregar al carrito

	/**
	 * Obtiene la lista de productos disponibles en el catálogo.
	 * 
	 * @return Lista de elementos WebElement que representan los productos.
	 */
	public List<WebElement> getProductList() {

		waitForElementToAppear(productsBy);
		return products;
	}

	/**
	 * Busca un producto en el catálogo por su nombre.
	 * 
	 * @param productName Nombre del producto a buscar.
	 * @return WebElement que representa el producto encontrado, o null si no se
	 *         encuentra.
	 */
	public WebElement getProductByName(String productName) {

		return getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().trim().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}

	/**
	 * Agrega un producto al carrito de compras basado en su nombre.
	 * 
	 * @param productName Nombre del producto a agregar.
	 * @throws InterruptedException Excepción en caso de interrupción en la espera.
	 */
	public void addProductToCar(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName).findElement(addToCart);

		prod.click();

		waitForElementToAppear(toastMessage); // Espera a que aparezca el mensaje de confirmación
		waitForElementToDisappear(spinner); // Espera a que desaparezca el indicador de carga
	}

}
