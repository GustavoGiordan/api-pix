package com.example.demo.resources;

import com.example.demo.models.Chave;
import com.example.demo.models.Cliente;
import com.example.demo.models.Entrada;
import com.example.demo.models.Saida;
import com.example.demo.repository.ChaveRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/chavePix")
public class ChaveResource {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ChaveRepository chaveRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listarClientes(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/buscaIdChave/{id}")
	public Chave buscarIdChave(@PathVariable(value="id") String id){
		return chaveRepository.findById(id).get();
	}

	@GetMapping("/buscaTipoChave/{chave}")
	public List<Chave> buscarTipoChave(@PathVariable(value="chave") String chave){
		return chaveRepository.findByTipoChave(chave);
	}

	@GetMapping("/buscaAgenciaConta/{agencia}/{conta}")
	public List<Chave> buscarAgenciaConta(@PathVariable(value="agencia") Long agencia, @PathVariable(value="conta") Long conta){
		 Cliente cli = clienteRepository.findByAgenciaAndConta(agencia, conta);
		 return chaveRepository.findByIdCliente(cli);
	}

	@GetMapping("/buscaNome/{nome}")
	public List<Chave> buscarNomecliente(@PathVariable(value="nome") String nome){
		 List<Cliente> cli = clienteRepository.findByNomeCliente(nome);
		if(cli.size() > 0 )
			return chaveRepository.findByIdCliente(cli.get(0));
		else 
		return new ArrayList<Chave>();
	}
	
	
	@PostMapping("/salvar")
	public ResponseEntity salvaChave(@RequestBody Entrada entrada) {

		Saida saida = Util.validaCamposChave(entrada);
		
		if(saida.getHttpCode() == 200){
			Cliente cli = salvarAgenciaConta(entrada);
			saida = salvarChave(entrada, cli);
		}
		
		return ResponseEntity.status(saida.getHttpCode())
		        .body(saida.getMensagem());		
	}

	@PutMapping("/alterar")
	public ResponseEntity alteraChave(@RequestBody Entrada entrada) {
		
		Saida saida = Util.validaCamposAlteracao(entrada);
		
		if(saida.getHttpCode() == 200){
			saida = alterarAgenciaConta(entrada);
		}
		
		return ResponseEntity.status(saida.getHttpCode())
		        .body(saida.getMensagem());		
	}

	@DeleteMapping("/inativar")
	public ResponseEntity inativaChave(@RequestBody Entrada entrada) {
		
		Saida saida = Util.validaCamposInativacao(entrada);
		
		if(saida.getHttpCode() == 200){
			saida = inativarChave(entrada);
		}
		
		return ResponseEntity.status(saida.getHttpCode())
		        .body(saida.getMensagem());		
	}
		
	public Cliente salvarAgenciaConta(Entrada entrada) {
		
		Cliente cli = clienteRepository.findByAgenciaAndConta(entrada.getAgencia(), entrada.getConta());
		
		if(cli == null) {
			cli = new Cliente(entrada.getNome(), entrada.getSobrenome(), entrada.getTipoConta(), entrada.getAgencia(), entrada.getConta());
			return clienteRepository.save(cli);
		}
		
		return cli;
	}

	public Saida salvarChave(Entrada entrada, Cliente cli){

		Saida saida = new Saida();
		saida.cadastrar("Chave salva com sucesso!", 200);

		List<Chave> listaChaves = chaveRepository.findByIdCliente(cli);
		
		if(cli.getTipoConta().contains("PF")){
			if(listaChaves.size() >= 4){
				saida.cadastrar("Limite de chaves cadastradas excedido para conta PF!", 422);
				return saida;
			} 
		} else if(cli.getTipoConta().contains("PJ")){
			if(listaChaves.size() >= 19){
				saida.cadastrar("Limite de chaves cadastradas excedido para conta PJ!", 422);
				return saida;
			}
		}

		Chave chave = chaveRepository.findByValorChave(entrada.getValorChave());
		if(chave != null){
			saida.cadastrar("Chave já cadastrada!", 422);
			return saida;
		}
	
		chave = chaveRepository.save(new Chave(UUID.randomUUID().toString(), entrada.getChave(), entrada.getValorChave(), cli));
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		saida.cadastrar("Chave cadastrada com sucesso - UUID = " + chave.getId() + " Data/Horario = " + dtf.format(LocalDateTime.now()), 200);

		return saida;
	}
	
	public Saida alterarAgenciaConta(Entrada entrada) {
		
		Chave chave = new Chave();
		Saida saida = new Saida();
		saida.cadastrar("Cliente alterado com sucesso!", 200);

		try {
			chave = chaveRepository.findById(entrada.getUuid()).get();
		} catch (Exception e) {
			saida.cadastrar("UUID não encontrado!", 404);
			return saida;
		}

		Cliente cli = new Cliente(entrada.getNome(), entrada.getSobrenome(), entrada.getTipoConta(), entrada.getAgencia(), entrada.getConta());
		cli.setIdCliente(chave.getIdCliente().getIdCliente());

		clienteRepository.save(cli);

		saida.setMensagem(Util.montarJsonAlteracao(cli, chave).toString());
		return saida;
	}

	public Saida inativarChave(Entrada entrada) {
		
		Chave chave = new Chave();
		Saida saida = new Saida();
		saida.cadastrar("Chave inativada com sucesso!", 200);

		try {
			chave = chaveRepository.findById(entrada.getUuid()).get();
		} catch (Exception e) {
			saida.cadastrar("UUID não encontrado!", 404);
			return saida;
		}

		if(chave.getStatus() == false){
			saida.cadastrar("Chave já inativada!", 422);
			return saida;
		}
		
		Cliente cli = clienteRepository.findById(chave.getIdCliente().getIdCliente()).get();

		Date dataInclusao = chave.getDataAlteracao();
		chave.setDataAlteracao(new Date());
		chave.setStatus(false);
		chaveRepository.save(chave);

		saida.setMensagem(Util.montarJsonInativacao(cli, dataInclusao).toString());
		return saida;
	}
	
	
}
