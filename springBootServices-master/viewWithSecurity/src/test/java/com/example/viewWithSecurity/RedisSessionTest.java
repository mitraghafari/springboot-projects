package com.example.viewWithSecurity;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSessionTest {

    private Jedis jedis;
    private TestRestTemplate testRestTemplate;
    private String url = "http://localhost:8082/login";

    @Before
    public void setup() {
        testRestTemplate = new TestRestTemplate("u", "p");

        jedis = new Jedis("localhost", 6379);
        jedis.flushAll();
    }

    @Test
    public void testSession() {

        Set<String> res = jedis.keys("*");
        Assert.assertEquals("redis is not empty",0, res.size());

        ResponseEntity<String> result = testRestTemplate.getForEntity(url, String.class);
        Assert.assertEquals("status is not OK",HttpStatus.OK, result.getStatusCode());

        res = jedis.keys("*");
        Assert.assertTrue("redis is empty",res.size()>0);


    }
}
