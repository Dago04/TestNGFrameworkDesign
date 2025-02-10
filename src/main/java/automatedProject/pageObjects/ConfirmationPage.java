package automatedProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automatedProject.abstractComponents.AbstractComponent;

/**
 * Clase ConfirmationPage que representa la página de confirmación de compra en la aplicación de eCommerce.
 * Extiende AbstractComponent para reutilizar métodos comunes.
 */
public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	 /**
     * Constructor de la clase ConfirmationPage.
     * 
     * @param driver WebDriver para interactuar con el navegador.
     */
	public ConfirmationPage(WebDriver driver) {

		super(driver); // Llama al constructor de la clase padre (AbstractComponent)
		this.driver = driver;
		PageFactory.initElements(driver, this); // Inicializa los elementos web de la página
	}

	// Localizador del mensaje de confirmación de la compra
	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;
	
	
	 /**
     * Obtiene el texto del mensaje de confirmación de la compra.
     * 
     * @return Texto del mensaje de confirmación.
     */
	public String getConfirmMessageText() {
		
		return confirmMessage.getText(); 
	}
	

}
