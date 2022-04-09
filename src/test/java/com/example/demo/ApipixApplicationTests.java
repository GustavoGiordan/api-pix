package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApipixApplicationTests {

	@Autowired
	private MockMvc mockMvc;
  
	@Autowired
	private ObjectMapper objectMapper;
  
	@Test
	void contextLoads() {
	}

	@Test
	void buscarPorId() throws Exception {

		this.mockMvc.perform(get("/chavePix/buscaNome/gustavo")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		//.andExpect(jsonPath("$[0].name", is("bob")));
	}

}
