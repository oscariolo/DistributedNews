package com.springbootserver.distributednewsserver.config;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.apache.kafka.common.serialization.StringSerializer;

@Configuration
public class NewsProducerFactoryConfig {

    @Autowired
    private KafkaProperties kafkaProperties;
    
    @Bean
    public ProducerFactory<String,String> producerFactory() {
        // Create a ProducerFactory with the specified properties
       var configs = new HashMap<String,Object>();
       configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
       configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        // Create a KafkaTemplate with the ProducerFactory
        return new KafkaTemplate<>(producerFactory());
    }
    
}
