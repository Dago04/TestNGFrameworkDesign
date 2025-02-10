package automatedProject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automatedProject.abstractComponents.AbstractComponent;

/**
* Clase LandingPage que representa la página de inicio de sesión en la aplicación de eCommerce.
* Extiende AbstractComponent para reutilizar funcionalidades comunes.
*/
public class LandingPage extends AbstractComponent{

	WebDriver driver;

	/**
     * Constructor de la clase LandingPage.
     * 
     * @param driver WebDriver para interactuar con el navegador.
     */
	public LandingPage(WebDriver driver) {
		
		super(driver); // Llama al constructor de la clase padre (AbstractComponent)
		this.driver = driver;
		PageFactory.initElements(driver, this); // Inicializa los elementos web de la página
	}

	
	 // Localizadores de elementos
	
	//PageFactory 
	@FindBy(id="userEmail")
	WebElement userEmail;// Campo de entrada para el correo electrónico
	
	@FindBy(id="userPassword")
	WebElement userPassword; // Campo de entrada para la contraseña
	
	@FindBy(id="login")
	WebElement btnLogin; // Botón para iniciar sesión
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage; // Mensaje de error en caso de credenciales inválidas
	
	
	
	 /**
     * Realiza el inicio de sesión en la aplicación con el correo y la contraseña proporcionados.
     * 
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return Instancia de ProductCatalogue si el login es exitoso.
     */
	public ProductCatalogue loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		btnLogin.click();
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	/**
     * Obtiene el mensaje de error que aparece al fallar el inicio de sesión.
     * 
     * @return Texto del mensaje de error.
     */
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	/**
     * Navega a la URL de la aplicación web.
     */
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
