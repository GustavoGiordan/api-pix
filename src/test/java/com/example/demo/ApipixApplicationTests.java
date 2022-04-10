package com.example.demo;

import com.example.demo.models.Cliente;
import com.example.demo.models.Entrada;
import com.example.demo.models.Chave;
import com.example.demo.models.Saida;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class ApipixApplicationTests {

	@Autowired
	private MockMvc mockMvc;
  
	@Test
	void contextLoads() {
	}

	@Test
	void buscarId() throws Exception {
		this.mockMvc.perform(get("/chavePix/buscaNome/gustavo")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		assertTrue(true);
	}

	@Test
	void buscarId2() throws Exception {
		this.mockMvc.perform(get("/chavePix/buscaNome/Teste")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		assertTrue(true);
	}

	@Test
	void buscarIdVazio() throws Exception {
		this.mockMvc.perform(get("/chavePix/buscaNome/naoExiste")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		assertTrue(true);
	}

	@Test
	void buscarAgCt() throws Exception {
		this.mockMvc.perform(get("/chavePix/buscaAgenciaConta/1001/1001")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		assertTrue(true);
	}

	@Test
	void buscarTpChave() throws Exception {
		this.mockMvc.perform(get("/chavePix/buscaTipoChave/celular")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		assertTrue(true);
	}

	@Test
	void buscarIdChave() throws Exception {
		this.mockMvc.perform(get("/chavePix/buscaIdChave/4d93eb04-60d3-4961-b219-091cea13814d")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		assertTrue(true);
	}

	@Test
	void buscarClientes() throws Exception {
		this.mockMvc.perform(get("/chavePix/clientes")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		assertTrue(true);
	}


	@Test
	void salvarChaveSucesso() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "celular", "+"+random.nextInt(9)+"511"+random.nextInt(9)+"3115"+ random.nextInt(9)+"97", "CorrentePF", Long.valueOf(random.nextInt(9) + numero), Long.valueOf(random.nextInt(9) + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().isOk());

		assertTrue(true);
	}

	@Test
	void salvarChaveJaCadastrada() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "celular", "+5511831152970", "CorrentePF", Long.valueOf(random.nextInt(9) + numero), Long.valueOf(random.nextInt(9) + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	void salvarChaveLimitePF() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);
		int numero2 = random.nextInt(9);
		int numero3 = random.nextInt(9);

		Entrada ent = new Entrada("", "celular", "", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveFaltandoChave() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "", "teste"+ numero +"@email.com", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveIncorreta() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "teste", "teste"+ numero +"@email.com", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveCPFInvalido() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "cpf", "123", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveCNPJInvalido() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "cnpj", "1234567890123456789", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveAleatorioInvalido() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "1234567890123456789012345678901234567890", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveEmailInvalido() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "email", "1234567890123456789012345678901234567890", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveCelularInvalido() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "celular", "1234567890123456789012345678901234567890", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveCelularInvalido2() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "celular", "+abc", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveCelularInvalido3() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "celular", "+123", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveCelularInvalido4() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "celular", "+1234567890123456", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveAgenciaVazia() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345678", "CorrentePF", null, Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveAgenciaVazia2() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345678", "CorrentePF", Long.parseLong("0"), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveContaVazia() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345678", "CorrentePF", Long.valueOf("1" + numero), null,
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveContaVazia2() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345678", "CorrentePF", Long.valueOf("1" + numero), Long.parseLong("0"),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveValorVazia() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "celular", null, "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	void salvarChaveValorVazia2() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "celular", "", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveTpContaVazia() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "email", "12345@teste", null, Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveTpContaVazia2() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "email", "12345@teste", "", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveTpContaSemPF() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345", "Corrente", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste" + numero, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	
	@Test
	void salvarChaveNomeVazio() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveNomeVazio2() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		null, "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveNomeMais35Caracteres() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"1234567890123456789012345678901234567890", "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveSobrenomeMais45Caracteres() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345", "CorrentePF", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste", "12345678901234567890123456789012345678901234567890");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveTpContaMais10Caracteres() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "aleatoria", "12345", "CorrentePF123", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste", "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void salvarChaveTpContaInvalido() throws Exception {

		Random random = new Random();
		int numero = random.nextInt(100);

		Entrada ent = new Entrada("", "email", "12345@teste.com", "teste", Long.valueOf("1" + numero), Long.valueOf("1" + numero),
		"teste", "");
		JSONObject json = ApipixUtilTests.montarJsonInsert(ent);
		

		this.mockMvc.perform(post("/chavePix/salvar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}



	@Test
	void AlterarChaveSucesso() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "CorrentePF", Long.valueOf("1001"), Long.valueOf("1001"),
		"Teste", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().isOk());

		assertTrue(true);
	}

	@Test
	void AlterarChave404() throws Exception {

		Entrada ent = new Entrada("00", "", "", "CorrentePF", Long.valueOf("1001"), Long.valueOf("1001"),
		"Teste", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(404));

		assertTrue(true);
	}

	@Test
	void AlterarChaveSemUUID() throws Exception {

		Entrada ent = new Entrada("", "", "", "CorrentePF", Long.valueOf("1001"), Long.valueOf("1001"),
		"Teste", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveSemTpConta() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "", Long.valueOf("1001"), Long.valueOf("1001"),
		"Teste", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}	

	@Test
	void AlterarChaveSemTpConta2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", null, Long.valueOf("1001"), Long.valueOf("1001"),
		"Teste", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveSemConta() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "CorrentePF", null, Long.valueOf("1001"),
		"Teste", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveSemAg() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "CorrentePF", Long.valueOf("1001"), null,
		"Teste", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveSemNome() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "CorrentePF", Long.valueOf("1001"), Long.valueOf("1001"),
		"", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveSemNome2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "CorrentePF", Long.valueOf("1001"), Long.valueOf("1001"),
		null, "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveSemTpContaPF() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "Corrente", Long.valueOf("1001"), Long.valueOf("1001"),
		"teste", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveNomeMais30caracteres() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "CorrentePF", Long.valueOf("1001"), Long.valueOf("1001"),
		"12345678901234567890123456789012", "Unitario");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveSobrenomeMais45caracteres() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "CorrentePF", Long.valueOf("1001"), Long.valueOf("1001"),
		"teste", "12345678901234567890123456789012345678901234567890");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveTpContaMais10Caracteres() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "CorrentePF123", Long.valueOf("1001"), Long.valueOf("1001"),
		"teste", "sobrenome");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void AlterarChaveTpContIncorreto() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "", "teste", Long.valueOf("1001"), Long.valueOf("1001"),
		"teste", "sobrenome");
		JSONObject json = ApipixUtilTests.montarJsonAlterar(ent);
		

		this.mockMvc.perform(put("/chavePix/alterar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChave() throws Exception {


		MvcResult result = this.mockMvc.perform(get("/chavePix/buscaTipoChave/celular")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andReturn();

		JSONArray array = new JSONArray(result.getResponse().getContentAsString());
		JSONObject object = new JSONObject();

		for(int i=0; i < array.length(); i++)   
		{  
			object = array.getJSONObject(i);  
		} 

		Entrada ent = new Entrada(object.getString("id"), "celular", "+5511995706517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().isOk());

		assertTrue(true);
	}


	@Test
	void ApagarChaveJaInativa() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "celular", "+5511995706517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveNaoEncontrada() throws Exception {

		Entrada ent = new Entrada("abcd", "celular", "+5511995706517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(404));

		assertTrue(true);
	}

	@Test
	void ApagarChaveSemUUID() throws Exception {

		Entrada ent = new Entrada("", "celular", "+5511995706517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveSemUUID2() throws Exception {

		Entrada ent = new Entrada(null, "celular", "+5511995706517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveSemTpChave() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "", "+5511995706517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveSemTpChave2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", null, "+5511995706517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveSemValorChave() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "Celular", "", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveSemValorChave2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "Celular", null, "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveSemTpConta() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "Celular", "+66995706517", "", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveSemTpConta2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "Celular", "+66995706517", null, null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveTpContaInvalido() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "Celular", "+66995706517", "teste", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveCelularInvalido() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "Celular", "66995706517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveCelularInvalido2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "Celular", "+17", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveCelularInvalido3() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "Celular", "+abc", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveCelularInvalido4() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "Celular", "+1234567890123456789012345", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveEmailInvalido() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "email", "66995706517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveEmailInvalido2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "email", "6699575655555555555555544444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444406517", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChavecpfInvalido() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "cpf", "123", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChavecpfInvalido2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "cpf", "abc", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChavecpfInvalido3() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "cpf", "123456789012345", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveCnpjInvalido() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "cnpj", "abc", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveCnpjInvalido2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "cnpj", "123", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveCnpjInvalido3() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "cnpj", "1234567890123456789", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveAleatoriaInvalido() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "aleatoria", "abc", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveAleatoriaInvalido2() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "aleatoria", "1234567890123456789012345678901234567890", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void ApagarChaveInvalido() throws Exception {

		Entrada ent = new Entrada("4d93eb04-60d3-4961-b219-091cea13814d", "teste", "abc", "CorrentePF", null, null,
		"", "");
		JSONObject json = ApipixUtilTests.montarJsonDelete(ent);
		

		this.mockMvc.perform(delete("/chavePix/inativar")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json.toString()))
		.andExpect(status().is(422));

		assertTrue(true);
	}

	@Test
	void testeCliente() throws Exception {

		Cliente cli = new Cliente();
		cli.setAgencia(Long.parseLong("1"));
		cli.setConta(Long.parseLong("1"));
		cli.setIdCliente(Long.parseLong("1"));
		cli.setNomeCliente("nomeCliente");
		cli.setSobrenomeCliente("sobrenomeCliente");
		cli.setTipoConta("tipoConta");
		
		assertTrue(true);

	}

	@Test
	void testeChave() throws Exception {

		Chave chave = new Chave();

		chave.setDataAlteracao(new Date());
		chave.setId("id");
		chave.setIdCliente(new Cliente());
		chave.setStatus(true);
		chave.setTipoChave("tipoChave");
		chave.setValorChave("valorChave");

		assertTrue(true);
		
	}

	@Test
	void testeSaida() throws Exception {

		Saida saida = new Saida();

		saida.setHttpCode(200);

		assertTrue(true);
		
	}
}
