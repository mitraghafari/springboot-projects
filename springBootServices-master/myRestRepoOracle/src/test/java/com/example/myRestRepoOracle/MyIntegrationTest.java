package com.example.myRestRepoOracle;

import com.example.entity.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class MyIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void call_post() throws Exception {

		Car car=new Car(new Long(45646),"dfvxfv");

		mockMvc.perform(post("/cars")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(car))
		.accept(MediaType.APPLICATION_JSON))
				.andDo(print());

	}

	@Test
	public void call_get() throws Exception {
		mockMvc.perform(get("/cars")
		.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andReturn();
	}

}
