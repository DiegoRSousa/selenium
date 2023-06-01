package leilao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeiloesTest {

	private LeiloesPage paginaDeLeiloes;
	private CadastroLeilaoPage paginaDeCadastro;
	
	@BeforeEach
	void beforeEach() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		paginaDeLeiloes = paginaDeLogin.efetuarLogin();
		paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
	}

	@AfterEach
	void afterEach() {
		paginaDeLeiloes.fecharPagina();
	}

	@Test
	void deveriaCadastrarLeilao() {
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do dia" + hoje;
		String valor = "500.00";
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
		
		assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
	}
	
	@Test
	void deveriaValidarCadastroDeLeilao() {
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("", "", "");
		
		assertFalse(this.paginaDeCadastro.isPaginaAtual());
		assertTrue(this.paginaDeLeiloes.isPaginaAtual());
		assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiveis());
	}
	
	
}
