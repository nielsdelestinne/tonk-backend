package be.niedel.tonk.infrastructure.program.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "be.niedel.tonk.infrastructure",
        "be.niedel.tonk.adapter"
})
public class SpringBootProgram{

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProgram.class, args);
    }

}
