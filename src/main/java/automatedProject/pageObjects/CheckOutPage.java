package automatedProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automatedProject.abstractComponents.AbstractComponent;


/**
 * Clase CheckOutPage que representa la página de checkout en la aplicación de eCommerce.
 * Extiende AbstractComponent para heredar funcionalidades reutilizables.
 */
public class CheckOutPage extends AbstractComponent {
 
	WebDriver driver;

	/**
     * Constructor de la clase CheckOutPage.
     * 
     * @param driver WebDriver para interactuar con el navegador.
     */
	public CheckOutPage(WebDriver driver) {

		super(driver);  // Llama al constructor de la clase padre (AbstractComponent)
		this.driver = driver;
		PageFactory.initElements(driver, this); // Inicializa los elementos web de la página
	}
	
	 // Localizadores de elementos web en la página de checkout

	@FindBy(css = "[placeholder*='Country']")
	WebElement selectCountry; // Campo de entrada para seleccionar el país
 
	@FindBy(css = ".ta-results span")
	List<WebElement> suggestionList; // Lista de sugerencias desplegadas al ingresar el país
	
	@FindBy(css = ".action__submit")
	WebElement btnPlaceOrder;  // Botón para confirmar la orden

	By suggestions = By.cssSelector(".ta-results"); // Selector para la lista de sugerencias
	By toastMessage = By.cssSelector("#toast-container"); // Selector del mensaje emergente tras enviar la orden
	
	
	 /**
     * Selecciona un país de la lista desplegable ingresando su nombre.
     * 
     * @param countryName Nombre del país a seleccionar.
     */
	public void selectCountry(String countryName) {
		selectCountry.sendKeys(countryName);
		waitForElementToAppear(suggestions); // Espera a que aparezca la lista de sugerencias
		
		for (WebElement suggestion : suggestionList) {
			if (suggestion.getText().equalsIgnoreCase(countryName)) {
				suggestion.click();
				break;
			}
		}
	}

	/**
     * Hace clic en el botón de "Place Order" y redirige a la página de confirmación del pedido.
     * 
     * @return Instancia de ConfirmationPage.
     */
	public ConfirmationPage submitOrder() {
		
		btnPlaceOrder.click();
		waitForElementToAppear(toastMessage); //Espera a que aparezca el mensaje de confirmación
		
		ConfirmationPage orderPage = new ConfirmationPage(driver);
		return orderPage;
	}

}
