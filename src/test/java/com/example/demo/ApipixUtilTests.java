package com.example.demo;

import com.example.demo.models.Entrada;

import org.json.JSONException;
import org.json.JSONObject;

public class ApipixUtilTests {
    
    public static JSONObject montarJsonInsert(Entrada ent) {

		JSONObject json = new JSONObject();


		try {
            json.put("id", ent.getUuid());
            json.put("chave", ent.getChave());
            json.put("valorChave", ent.getValorChave());
            json.put("tipoConta", ent.getTipoConta());
            json.put("agencia", ent.getAgencia());
            json.put("conta", ent.getConta());
            json.put("nome", ent.getNome());
            json.put("sobrenome", ent.getSobrenome());
        } catch (JSONException e) {
            return null;
        }

        return json;

    }

    public static JSONObject montarJsonAlterar(Entrada ent) {

		JSONObject json = new JSONObject();


		try {
            json.put("uuid", ent.getUuid());
            json.put("tipoConta", ent.getTipoConta());
            json.put("agencia", ent.getAgencia());
            json.put("conta", ent.getConta());
            json.put("nome", ent.getNome());
            json.put("sobrenome", ent.getSobrenome());
        } catch (JSONException e) {
            return null;
        }

        return json;

    }

    public static JSONObject montarJsonDelete(Entrada ent) {

		JSONObject json = new JSONObject();


		try {
            json.put("uuid", ent.getUuid());
            json.put("chave", ent.getChave());
            json.put("valorChave", ent.getValorChave());
            json.put("tipoConta", ent.getTipoConta());
        } catch (JSONException e) {
            return null;
        }

        return json;

    }

}
