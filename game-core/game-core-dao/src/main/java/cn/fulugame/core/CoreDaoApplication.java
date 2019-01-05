package cn.fulugame.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.fulugame")
public class CoreDaoApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(CoreDaoApplication.class, args);
    }

}
