package mk.ukim.finki.balloon.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

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

}
