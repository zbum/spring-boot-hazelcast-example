package com.nhnacademy.edu.springboot.hazelcastex1;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public IQueue<String> nameQueue(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getQueue("name");
    }
}
