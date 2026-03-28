package com.booknetwork.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
public class FileServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileServiceApplication.class, args);
    }
}
