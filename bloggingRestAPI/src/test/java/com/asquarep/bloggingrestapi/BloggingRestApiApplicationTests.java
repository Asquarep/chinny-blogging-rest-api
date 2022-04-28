package com.asquarep.bloggingrestapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
class BloggingRestApiApplicationTests {
    private final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("psql:latest");

    @Test
    void contextLoads() {
        System.out.println(postgreSQLContainer.getDatabaseName());
    }

}
