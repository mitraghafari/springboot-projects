package com.example.viewWithSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ViewWithSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewWithSecurityApplication.class, args);
	}

	@Bean("normalRestTemplate")
	RestTemplate normRestTemplate(){
		return new RestTemplate();
	}


	@Autowired
	private ObjectMapper objectMapper;

	/**
	 *
	 * @return a {@link RestTemplate} with a HAL converter
	 */
	@Bean("hyperTextRestTemplate")
	public RestTemplate restTemplate() {

		// converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(HAL_JSON));
		converter.setObjectMapper(objectMapper);

		RestTemplate restTemplate = new RestTemplate(Collections.singletonList(converter));

		return restTemplate;
	}

}
