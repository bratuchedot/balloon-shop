package mk.ukim.finki.balloon.shop.selenium;

import mk.ukim.finki.balloon.shop.model.Balloon;
import mk.ukim.finki.balloon.shop.model.Manufacturer;
import mk.ukim.finki.balloon.shop.model.User;
import mk.ukim.finki.balloon.shop.service.AuthService;
import mk.ukim.finki.balloon.shop.service.BalloonService;
import mk.ukim.finki.balloon.shop.service.ManufacturerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    AuthService authService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;

    private HtmlUnitDriver driver;

    private static Manufacturer m1;
    private static Optional<Balloon> b1;
    private static User adminUser;

    private static String admin = "admin";

    private static boolean dataInitialized = false;


    @BeforeEach
    private void setup() throws Exception {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() throws Exception {
        if (!dataInitialized) {
            m1 = manufacturerService.save("m1", "m1", "m1", LocalDate.now()).get();

            b1 = balloonService.save("b1","b1", m1.getId());

//            TODO: the code of the line below does not create admin user, fix required
            adminUser = authService.register(admin, admin, admin, admin, admin, LocalDate.now());

            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        BalloonPage balloonPage = BalloonPage.to(this.driver);
        balloonPage.assertElemts(1, 0, 0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

//        TODO: check buttons after login with admin user
//        balloonPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
//        balloonPage.assertElemts(1, 1, 1, 1, 1);
    }


}
