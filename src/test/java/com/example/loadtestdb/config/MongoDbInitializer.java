package com.example.loadtestdb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@Component
public class MongoDbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, DisposableBean {

    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:5.0.2"));

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        mongoDBContainer.start();
        log.info("Overriding Spring Properties for mongodb !!!!!!!!!");

        TestPropertyValues values = TestPropertyValues.of(
                "spring.data.mongodb.host=" + mongoDBContainer.getContainerIpAddress(),
                "spring.data.mongodb.port=" + mongoDBContainer.getMappedPort(27017)

        );
        values.applyTo(configurableApplicationContext);

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy mongoDB");
        mongoDBContainer.stop();
    }
}

