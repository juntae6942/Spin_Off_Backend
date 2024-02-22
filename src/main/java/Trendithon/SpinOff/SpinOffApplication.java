package Trendithon.SpinOff;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = {@Server(url = "https://likelion-running.store")})
@SpringBootApplication
public class SpinOffApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpinOffApplication.class, args);
    }

}
