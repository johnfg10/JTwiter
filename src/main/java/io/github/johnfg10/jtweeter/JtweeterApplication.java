package io.github.johnfg10.jtweeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JtweeterApplication  {
    @PostConstruct
    public void init(){
    }

    public static void main(String[] args) {
        SpringApplication.run(JtweeterApplication.class, args);
    }
}
