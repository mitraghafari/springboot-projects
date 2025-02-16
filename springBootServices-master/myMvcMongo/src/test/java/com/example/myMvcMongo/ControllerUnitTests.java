package com.example.myMvcMongo;

import com.example.controller.UserController;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//just load web layer
@WebMvcTest(UserController.class)//: it is good for unit test of web layer, when there is no dep to button layers

public class ControllerUnitTests {

    @MockBean  //We can use the @MockBean to add mock objects to the Spring application context.
    // The mock will replace any existing bean of the same type in the application context. it is useful for integration test
    private UserRepository userRepository;

    //or

//    @Mock //create a mock object of a class or an interface.
    //note that when we want to mock an inside class which is not called explicitly(like here),
    //using this mock does not help, because the bean that is injected in context should be mocked
//    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private final List<User> list=new ArrayList<>();
    private final User user1 = new User(new BigInteger("1"), "zsd", "abc1");
    private final User user2 = new User(new BigInteger("2"), "zsd", "abc2");

    @Before
    public void setup(){
        list.add(user1);

    }

    @Test
    public void callGetAllUsers() throws Exception {


        when(userRepository.findAll()).thenReturn(list);

        mockMvc.perform(get("/mongo/users"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(user1.getName())));

    }

    @Test
    public void call_post() throws Exception {

        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user2);

        mockMvc.perform(post("/mongo/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user2))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.family",is(user2.getFamily())));

    }

}
