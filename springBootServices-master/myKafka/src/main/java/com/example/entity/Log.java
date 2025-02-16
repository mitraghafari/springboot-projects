package com.example.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "log_tbl")
public class Log {

    @Id
    private int id;

    private String topic;
    private String mess;
    private Date insertTime;



    public Log(int id, String topic,String mess, Date insertTime) {

        this.id = id;
        this.topic=topic;
        this.mess = mess;
        this.insertTime = insertTime;
    }
}
