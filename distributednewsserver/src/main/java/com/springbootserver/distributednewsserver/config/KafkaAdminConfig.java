package com.springbootserver.distributednewsserver.config;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaAdminConfig {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        //Servidor de arranque de kafka
        var configs = new HashMap<String,Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return new KafkaAdmin(configs);
    }

    // @Bean
    // public KafkaAdmin.NewTopics newTopics() {
    //     // Crear servidor de arranque, crea los topics y las particiones
    //     // Create a new topic with the specified name and configuration
    //     // return new KafkaAdmin.NewTopics(
    //     //         TopicBuilder.name("default-topic")
    //     //                 .partitions(2)
    //     //                 .replicas(1)
    //     //                 .build()
    //     // );
    // }
    
}
