package leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject{
	
	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	public LoginPage() {
		super(null);
		browser.navigate().to(URL_LOGIN);
	}
	
	public void preencherFormularioDeLogin(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
	}
	
	public LeiloesPage efetuarLogin() {
		browser.findElement(By.id("login-form")).submit();
		return new LeiloesPage(browser);
	}
	
	public void navegarParaPaginaDeLances() {
		browser.navigate().to("http://localhost:8080/leiloes/2");
	}
	
	public boolean isPaginaDeLogin() {
		return browser.getCurrentUrl().equals(URL_LOGIN);
	}
	
	public boolean isPaginaDeLoginComDadosInvalidos () {
		return browser.getCurrentUrl().equals("http://localhost:8080/login?error");
	}
	
	public boolean contemTexto(String texto) {
		return browser.getPageSource().contains(texto);
	}

	public String getNomeUsuarioLogado() {
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
		} catch(NoSuchElementException e) {
			return null;
		}
		
		
	}
}
