package com.lkulig.ratpack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ratpack.spring.config.EnableRatpack;

@EnableRatpack
@SpringBootApplication
@EnableJpaRepositories("com.lkulig.ratpack.repository")
@EnableTransactionManagement
public class RatpackDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatpackDemoApplication.class, args);
    }

}
