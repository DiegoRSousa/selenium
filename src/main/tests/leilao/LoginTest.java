package leilao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	private static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver browser;
	
	@BeforeAll
	static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	}
	
	@BeforeEach
	void beforeEach() {
		browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}
	
	@AfterEach
	void afterEach() {
		browser.quit();
		
	}
	
	@Test
	void deveriaEfetuarLoginComDadosValidos() {
		
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();
		
		assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
		assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
		
	}
	
	@Test
	void naoDeveriaLogarComUsuarioInvalido() {
		
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("1234");
		browser.findElement(By.id("login-form")).submit();
		
		assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
		assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));
		assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText()); 
	}

}
