package com.asquarep.bloggingrestapi;

import com.asquarep.bloggingrestapi.model.Blogger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingRestApiApplication {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(BloggingRestApiApplication.class, args);

        Blogger blogger = new Blogger();
    }

}
