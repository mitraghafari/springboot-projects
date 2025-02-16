package com.example.myMvcMongo;

import com.example.controller.UserController;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@SpringBootTest //load complete spring context
@AutoConfigureMockMvc  //this two lines are good for integration test, because initial complete application context

public class ControllerIntegrationTest {

//	@Autowired
//	private TestRestTemplate restTemplate;

    //or
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private UserRepository userRepository;

    private final User user1 = new User(new BigInteger("1"), "zsd", "abc1");
    private final User user2 = new User(new BigInteger("2"), "zsd", "abc2");

    @Before
    public void setUp() {

        userRepository.save(user1);
    }

    //when output is list, to get data use $[n].name
    //when output is single user, to get data use $.name
    @Test
    public void callGetbyId() throws Exception {
        mockMvc.perform(get("/mongo/users/"+user1.getId()))
                .andDo(print())
                .andExpect(jsonPath("$.family", CoreMatchers.is("abc1")));

    }

    @Test
    public void call_post() throws Exception {

        mockMvc.perform(post("/mongo/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user2))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name",CoreMatchers.is(user2.getName())));

    }

}
