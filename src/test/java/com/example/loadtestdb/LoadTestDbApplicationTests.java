package com.example.loadtestdb;

import com.example.loadtestdb.config.MongoDbInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@SpringBootTest
@ContextConfiguration(initializers = MongoDbInitializer.class)
class LoadTestDbApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }

}
