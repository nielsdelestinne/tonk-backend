package be.niedel.tonk.adapter.webapi.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "be.niedel.tonk.adapter"
})
public class SpringBootTestProgram{

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestProgram.class, args);
    }

}
