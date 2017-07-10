package fuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zeev on 7/10/17.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }


}
