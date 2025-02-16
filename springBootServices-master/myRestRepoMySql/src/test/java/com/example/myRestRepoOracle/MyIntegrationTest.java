package com.example.myRestRepoOracle;

import com.example.entity.Car;
import com.example.repository.CarRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class MyIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CarRepository carRepository;

	private final Car car1=new Car(new Long(1),"mvm");
	private final Car car2=new Car(new Long(2),"bmv");

	@Before
	public void setup(){
		carRepository.save(car2);
	}

	@After
	public void cleanup(){
		carRepository.delete(car1);
		carRepository.delete(car2);
	}

	@Test
	public void test_post() throws Exception {

		mockMvc.perform(post("/cars")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(car1))
		.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.model",is(car1.getModel())));

	}

	@Test
	public void testGetByID() throws Exception {
		mockMvc.perform(get("/cars/"+car2.getId())
		.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.model",is(car2.getModel())));
	}

}
