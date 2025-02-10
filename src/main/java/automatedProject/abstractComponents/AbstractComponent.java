package automatedProject.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automatedProject.pageObjects.CartPage;
import automatedProject.pageObjects.OrderPage;

/**
 * Clase AbstractComponent que contiene métodos reutilizables para la
 * automatización de pruebas. Esta clase es heredada por todas las clases Page
 * Object Model (POM) para evitar la repetición de código.
 */
public class AbstractComponent {
	WebDriver driver;

	/**
	 * Constructor de AbstractComponent.
	 * 
	 * @param driver WebDriver para interactuar con el navegador.
	 */
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Localizadores de elementos
	@FindBy(css = "[routerlink*='cart']")
	WebElement btnCart; // Botón para acceder al carrito de compras

	@FindBy(css = "[routerlink*='orders']")
	WebElement btnOrders; // Botón para acceder a las órdenes del usuario

	// Métodos de Espera Explícita

	/**
	 * Espera a que un elemento sea visible en la página antes de interactuar con
	 * él.
	 * 
	 * @param findBy Localizador del elemento (By).
	 */
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	/**
	 * Espera a que un WebElement específico sea visible en la página.
	 * 
	 * @param findBy WebElement que se espera que aparezca.
	 */

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	/**
	 * Espera a que un WebElement desaparezca de la página.
	 * 
	 * @param element WebElement que se espera que desaparezca.
	 * @throws InterruptedException Excepción en caso de interrupción en la espera.
	 */
	public void waitForElementToDisappear(WebElement element) throws InterruptedException {

		Thread.sleep(1000);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.invisibilityOf(element));
	}

	// Métodos de Navegación

	/**
	 * Navega a la página del carrito de compras.
	 * 
	 * @return CartPage Página del carrito de compras.
	 */
	public CartPage goToCartPage() {
		btnCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	/**
	 * Navega a la página de órdenes del usuario.
	 * 
	 * @return OrderPage Página de órdenes.
	 */
	public OrderPage goToOrdersPage() {
		btnOrders.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

}
