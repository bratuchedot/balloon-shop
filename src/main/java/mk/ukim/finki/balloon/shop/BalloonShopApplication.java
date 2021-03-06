package mk.ukim.finki.balloon.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Laboratory Exercises of Web Programming Course at FSCE - Skopje.
 *
 * @author Emilijan Koteski
 * Winter semester, 2021
 */

@SpringBootApplication
@ServletComponentScan
public class BalloonShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BalloonShopApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
