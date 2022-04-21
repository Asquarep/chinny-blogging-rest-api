package com.asquarep.bloggingrestapi;

import com.asquarep.bloggingrestapi.model.Blogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggingRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggingRestApiApplication.class, args);

        Blogger blogger = new Blogger();
    }

}
