package com.example.demo.util;

import java.util.Date;

import com.example.demo.models.Chave;
import com.example.demo.models.Cliente;
import com.example.demo.models.Entrada;
import com.example.demo.models.Saida;

import org.json.JSONObject;


public class Util {
    
    public static Saida validaCamposChave(Entrada entrada) {
		
		Saida saida = new Saida();
		saida.cadastrar("Validado com sucesso", 200);

        if(entrada.getAgencia() ==  null || entrada.getAgencia() == 0){
            saida.cadastrar("Agencia é um campo obrigatorio!", 422);
            return saida;
        } else if(entrada.getConta() == null || entrada.getConta() == 0){
            saida.cadastrar("Conta é um campo obrigatorio!", 422);
            return saida;
        } else if( entrada.getChave() == null || entrada.getChave().equals("")){
            saida.cadastrar("Tipo de chave é um campo obrigatorio!", 422);
            return saida;
        } else if(entrada.getValorChave() == null || entrada.getValorChave().equals("")){
            saida.cadastrar("Valor da chave é um campo obrigatorio!", 422);
            return saida;
        } else if(entrada.getTipoConta() == null || entrada.getTipoConta().equals("")){
            saida.cadastrar("Tipo de conta é um campo obrigatorio!", 422);
            return saida;
        }else if(!entrada.getTipoConta().contains("PF") && !entrada.getTipoConta().contains("PJ")){
            saida.cadastrar("Tipo conta deve conter o segmento do cliente(PF ou PJ)!", 422);
            return saida;
        } else if(entrada.getNome() == null || entrada.getNome().equals("")){
            saida.cadastrar("Nome do cliente é um campo obrigatorio!", 422);
            return saida;
        }

        if(entrada.getNome().length() > 30){
            saida.cadastrar("Nome do cliente não deve ultrapassar 30 caracteres!", 422);
            return saida;
        } else if(entrada.getSobrenome().length() > 45){
            saida.cadastrar("Sobrenome do cliente não deve ultrapassar 45 caracteres!", 422);
            return saida;
        } else if(entrada.getTipoConta().length() > 10){
            saida.cadastrar("Tipo de conta não deve ultrapassar 10 caracteres!", 422);
            return saida;
        } 

	
		if(entrada.getChave().equalsIgnoreCase("celular")) {
			if(!entrada.getValorChave().startsWith("+") || !entrada.getValorChave().matches("[+-]?\\d*(\\.\\d+)?") 
					|| entrada.getValorChave().length() > 14 || entrada.getValorChave().length() < 13) {
				saida.cadastrar("Celular invalido!", 422);
				return saida;
			}
			
		} else if(entrada.getChave().equalsIgnoreCase("email")) {
			if(!entrada.getValorChave().contains("@") || !(entrada.getValorChave().length() < 77)) {
				saida.cadastrar("Email invalido!", 422);
				return saida;
			}
		} else if(entrada.getChave().equalsIgnoreCase("cpf") || entrada.getChave().equalsIgnoreCase("cnpj")) {
			if(entrada.getChave().equalsIgnoreCase("cpf")){
				if(entrada.getValorChave().length() < 11 || entrada.getValorChave().length() > 11 || !entrada.getValorChave().matches("[+-]?\\d*(\\.\\d+)?")) {
					saida.cadastrar("CPF invalido!", 422);
					return saida;
				}
			} else{
				if(entrada.getValorChave().length() < 15 || entrada.getValorChave().length() > 15 || !entrada.getValorChave().matches("[+-]?\\d*(\\.\\d+)?")) {
					saida.cadastrar("CNPJ invalido!", 422);
					return saida;
				}
			}

		} else if(entrada.getChave().equalsIgnoreCase("aleatoria")) {
			if(!(entrada.getValorChave().length() < 36) || !entrada.getValorChave().matches("[+-]?\\d*(\\.\\d+)?")){
				saida.cadastrar("Chave invalida!", 422);
				return saida;
			}
		} else {
			saida.cadastrar("Tipo de chave inexistente!", 422);
			return saida;
		}
		
		return saida;		
	}

    public static Saida validaCamposAlteracao(Entrada entrada) {
		
		Saida saida = new Saida();
		saida.cadastrar("Validado com sucesso", 200);

        if(entrada.getUuid() == null || entrada.getUuid().equals("")){
            saida.cadastrar("UUID é um campo obrigatorio!", 422);
            return saida;
        }else if(entrada.getAgencia() ==  null || entrada.getAgencia() == 0){
            saida.cadastrar("Agencia é um campo obrigatorio!", 422);
            return saida;
        } else if(entrada.getConta() == null || entrada.getConta() == 0){
            saida.cadastrar("Conta é um campo obrigatorio!", 422);
            return saida;
        } else if(entrada.getTipoConta() == null || entrada.getTipoConta().equals("")){
            saida.cadastrar("Tipo de conta é um campo obrigatorio!", 422);
            return saida;
        }else if(!entrada.getTipoConta().contains("PF") && !entrada.getTipoConta().contains("PJ")){
            saida.cadastrar("Tipo conta deve conter o segmento do cliente(PF ou PJ)!", 422);
            return saida;
        } else if(entrada.getNome() == null || entrada.getNome().equals("")){
            saida.cadastrar("Nome do cliente é um campo obrigatorio!", 422);
            return saida;
        }

        if(entrada.getNome().length() > 30){
            saida.cadastrar("Nome do cliente não deve ultrapassar 30 caracteres!", 422);
            return saida;
        } else if(entrada.getSobrenome().length() > 45){
            saida.cadastrar("Sobrenome do cliente não deve ultrapassar 45 caracteres!", 422);
            return saida;
        } else if(entrada.getTipoConta().length() > 10){
            saida.cadastrar("Tipo de conta não deve ultrapassar 10 caracteres!", 422);
            return saida;
        } 
		
		return saida;		
	}

    public static Saida validaCamposInativacao(Entrada entrada) {
		
		Saida saida = new Saida();
		saida.cadastrar("Validado com sucesso", 200);

        if(entrada.getUuid() == null || entrada.getUuid().equals("")){
            saida.cadastrar("UUID é um campo obrigatorio!", 422);
            return saida;
        } else if( entrada.getChave() == null || entrada.getChave().equals("")){
            saida.cadastrar("Tipo de chave é um campo obrigatorio!", 422);
            return saida;
        } else if(entrada.getValorChave() == null || entrada.getValorChave().equals("")){
            saida.cadastrar("Valor da chave é um campo obrigatorio!", 422);
            return saida;
        } else if(entrada.getTipoConta() == null || entrada.getTipoConta().equals("")){
            saida.cadastrar("Tipo de conta é um campo obrigatorio!", 422);
            return saida;
        }

        if(!entrada.getTipoConta().contains("Poupanca") && !entrada.getTipoConta().contains("Corrente") 
            && !entrada.getTipoConta().contains("poupanca") && !entrada.getTipoConta().contains("corrente")){
            saida.cadastrar("Tipo de conta deve ser corrente ou Poupança!", 422);
            return saida;
        } 

        if(entrada.getChave().equalsIgnoreCase("celular")) {
			if(!entrada.getValorChave().startsWith("+") || !entrada.getValorChave().matches("[+-]?\\d*(\\.\\d+)?") 
					|| entrada.getValorChave().length() > 14 || entrada.getValorChave().length() < 13) {
				saida.cadastrar("Celular invalido!", 422);
				return saida;
			}
			
		} else if(entrada.getChave().equalsIgnoreCase("email")) {
			if(!entrada.getValorChave().contains("@") || !(entrada.getValorChave().length() < 77)) {
				saida.cadastrar("Email invalido!", 422);
				return saida;
			}
		} else if(entrada.getChave().equalsIgnoreCase("cpf") || entrada.getChave().equalsIgnoreCase("cnpj")) {
			if(entrada.getChave().equalsIgnoreCase("cpf")){
				if(entrada.getValorChave().length() < 11 || entrada.getValorChave().length() > 11 || !entrada.getValorChave().matches("[+-]?\\d*(\\.\\d+)?")) {
					saida.cadastrar("CPF invalido!", 422);
					return saida;
				}
			} else{
				if(entrada.getValorChave().length() < 15 || entrada.getValorChave().length() > 15 || !entrada.getValorChave().matches("[+-]?\\d*(\\.\\d+)?")) {
					saida.cadastrar("CNPJ invalido!", 422);
					return saida;
				}
			}

		} else if(entrada.getChave().equalsIgnoreCase("aleatoria")) {
			if(!(entrada.getValorChave().length() < 36) || !entrada.getValorChave().matches("[+-]?\\d*(\\.\\d+)?")){
				saida.cadastrar("Chave invalida!", 422);
				return saida;
			}
		} else {
			saida.cadastrar("Tipo de chave inexistente!", 422);
			return saida;
		}

		
		return saida;		
	}

    
    public static JSONObject montarJsonAlteracao(Cliente cli, Chave chave) {

		JSONObject json = new JSONObject();

		json.put("id", chave.getId());
		json.put("tipoChave", chave.getTipoChave());
		json.put("valorChave", chave.getValorChave());
		json.put("tipoConta", cli.getTipoConta());
		json.put("numeroAgencia", cli.getAgencia());
		json.put("numeroConta", cli.getConta());
		json.put("nomeCorrentista", cli.getNomeCliente());
		json.put("sobrenomeCorrentista", cli.getSobrenomeCliente());

        return json;

    }

    public static JSONObject montarJsonInativacao(Cliente cli, Date dataInclusao) {

		JSONObject json = new JSONObject();


		json.put("numeroAgencia", cli.getAgencia());
		json.put("numeroConta", cli.getConta());
		json.put("nomeCorrentista", cli.getNomeCliente());
		json.put("sobrenomeCorrentista", cli.getSobrenomeCliente());
        json.put("Datainclusao", dataInclusao);
        json.put("dataAlteracao", new Date());

        return json;

    }

}
