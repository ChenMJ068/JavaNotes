package c.mj.notes.demo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubboConfiguration
@SpringBootApplication
public class DubboDemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoProviderApplication.class, args);
    }

}
