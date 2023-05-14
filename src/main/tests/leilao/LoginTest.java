package leilao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
	
	private LoginPage paginaDeLogin;
	
	
	@BeforeEach
	void beforeEach() {
		paginaDeLogin = new LoginPage();
	}
	
	@AfterEach
	void afterEach() {
		paginaDeLogin.fecharPagina();
		
	}
	
	@Test
	void deveriaEfetuarLoginComDadosValidos() {
		
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		paginaDeLogin.efetuarLogin();
		
		assertFalse(paginaDeLogin.isPaginaDeLogin());
		assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
		
	}
	
	@Test
	void naoDeveriaLogarComUsuarioInvalido() {
		
		paginaDeLogin.preencherFormularioDeLogin("invalido", "123");
		paginaDeLogin.efetuarLogin();
		
		assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
		assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
		assertNull(paginaDeLogin.getNomeUsuarioLogado());
		 
	}
	
	@Test
	void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		paginaDeLogin.navegarParaPaginaDeLances();
		
		assertTrue(paginaDeLogin.isPaginaDeLogin());
		assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
	}

}
