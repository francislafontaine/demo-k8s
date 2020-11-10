package com.cloud.demo;

import com.cloud.demo.web.HelloWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCloudApplication.class, args);

        HelloWebClient gwc = new HelloWebClient();
        System.out.println(gwc.getResult());
    }
}
