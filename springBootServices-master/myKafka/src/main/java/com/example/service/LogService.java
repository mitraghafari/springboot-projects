package com.example.service;


import com.example.entity.Log;
import com.example.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public List<Log> findAll(String topic){

        return logRepository.findAllByTopic(topic);
    }


}
