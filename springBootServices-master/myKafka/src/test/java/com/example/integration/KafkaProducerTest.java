package com.example.integration;

import com.example.entity.Log;
import com.example.service.KafkaProducer;
import com.example.service.LogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext//Indicates that the underlying Spring ApplicationContext has been dirtied (modified)as follows during the execution of a test and should be closed,
public class KafkaProducerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerTest.class);

    private static String RECEIVER_TOPIC = "logs";

    @Autowired
    private LogService logService;

    @Autowired
    private KafkaProducer kafkaProducer;

    //private KafkaTemplate<String, String> template;

//    @Autowired
//    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

//    @ClassRule
//    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RECEIVER_TOPIC);

//    @Before
//    public void setUp() throws Exception {
//        // set up the Kafka producer properties
//        Map<String, Object> senderProperties =
//                KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
//
//        // create a Kafka producer factory
//        ProducerFactory<String, String> producerFactory =
//                new DefaultKafkaProducerFactory<String, String>(senderProperties);
//
//        // create a Kafka template
//        template = new KafkaTemplate<>(producerFactory);
//        // set the default topic to send to
//        template.setDefaultTopic(RECEIVER_TOPIC);
//
//        // wait until the partitions are assigned
//        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
//                .getListenerContainers()) {
//            ContainerTestUtils.waitForAssignment(messageListenerContainer,
//                    embeddedKafka.getPartitionsPerTopic());
//        }
//    }

    @Test
    public void testReceive() throws Exception {
        // send the message
//        String greeting = "Hello Spring Kafka Receiver!";
//        kafkaProducer.send(RECEIVER_TOPIC,greeting);
//        LOGGER.debug("test-sender sent message='{}'", greeting);
//
//        List<Log> logs=new ArrayList<>();
//        logs=logService.findAll(RECEIVER_TOPIC);
//        // check that the message was received
//        Assert.assertEquals(1,logs.size());
    }
}