package com.example.myRestRepoOracle;

import com.example.entity.Car;
import com.example.repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@DataJpaTest //it by default use a h2 in-mem db and need h2sql dep in pom. it takes db-properties from test-resources file
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//it disable default behaviour (up line)
// and use datasource properties in application.properties

public class MyRepoIntegrationTest {

	@Autowired
	private CarRepository carRepository;

	private final Car car1=new Car(new Long(1),"mvm");
	private final Car car2=new Car(new Long(2),"bmv");

	ExpectedException thrown =ExpectedException.none();

	@Before
	public void setup(){
		System.out.println("start of test");
	}

	@Test
	public void test_save()  {

		Car car=carRepository.save(car1);
		Assert.assertEquals("msg ", car1.getModel(),car.getModel());
	}

	@Test
	public void testGetByID()  {
		Optional<Car> car=carRepository.findById(car1.getId());
		car.ifPresent(value -> Assert.assertEquals("msg ", car1.getModel(), value.getModel()));

	}
//	@Test
//	public void testGetByNullId()  {
//		Optional<Car> car=carRepository.findById(null);
//		thrown.expect(Exception.class);
//	}

}
