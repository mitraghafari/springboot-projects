package com.example.listener;


import com.example.entity.Log;
import com.example.repository.LogRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Date;

public class KafkaConsumer {

    private final String topic="logs";

    private final String group_id="gid";

    Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    final private LogRepository logRepository;

    @Autowired
    public KafkaConsumer(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    @KafkaListener(topics = topic)//,containerFactory = "kafkaListenerContainerFactory" ,groupId = group_id)
    public void onMsg(@Payload String msg){
        LOGGER.info("received payload='{}'", msg);
        logRepository.save(new Log(2,topic, msg, new Date()));
    }


}
