package leilao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class LeiloesTest {

	private LeiloesPage paginaDeLeiloes;

	@AfterEach
	void afterEach() {
		paginaDeLeiloes.fecharPagina();
	}

	@Test
	void deveriaCadastrarLeilao() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		paginaDeLeiloes = paginaDeLogin.efetuarLogin();
		CadastroLeilaoPage paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
		
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do dia" + hoje;
		String valor = "500.00";
		paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
		
		assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
	}
}
