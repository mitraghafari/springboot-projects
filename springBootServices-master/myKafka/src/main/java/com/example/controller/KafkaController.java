package com.example.controller;


import com.example.entity.Log;
import com.example.service.KafkaProducer;
import com.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaService;
    private final LogService logService;

    @Autowired
    public KafkaController(KafkaProducer kafkaService,LogService logService) {
        this.kafkaService = kafkaService;
        this.logService=logService;
    }

    @RequestMapping("/add")
    public int send_msg(@RequestParam("msg") String msg,@RequestParam("topic") String topic) {
        kafkaService.send(topic, msg);
        return 1;//success
    }

    @RequestMapping("/getmsg/{topic}")
    public List<Log> getAllMsgOfTopic(@PathVariable String topic){
        return logService.findAll(topic);
    }

}

