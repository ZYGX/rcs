package com.huawei.rcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zygx
 */

@SpringBootApplication
@EnableDiscoveryClient
public class RcsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RcsApplication.class, args);
    }

}
