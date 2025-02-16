package com.example.myMvcMongo;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.Optional;

//@RunWith(MockitoJUnitRunner.class)//we do not need to load all spring context by @SpringBootTest
@RunWith(SpringRunner.class)
//@DataJpaTest //load just jpa-related part of spring context
@DataMongoTest // load just data-mongo part of spring context

public class RepoIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private final User user1=new User(new BigInteger("1"),"zsd","abc1");
    private final User user2=new User(new BigInteger("2"),"zsd","abc2");
    private final User user3=new User(new BigInteger("3"),"zsd","abc3");

    @Before
    public void setUp(){

        userRepository.save(user1);
    }

    @Test
    public void testSave(){

        User u=userRepository.save(user2);
        Assert.assertEquals("save is not correct",user1.getName(),u.getName());

    }

    @Test
    public void testFind(){
        Optional<User> u=userRepository.findById(user1.getId());
        Assert.assertEquals("find user by id is not correct ",user1.getName(), u.get().getName());

    }

}
