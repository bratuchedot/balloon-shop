package mk.ukim.finki.balloon.shop.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonPage extends AbstractPage {

    @FindBy(css = "tr[class=balloon]")
    private List<WebElement> balloonRows;

    @FindBy(css = ".delete-balloon")
    private List<WebElement> deleteButtons;

    @FindBy(className = "edit-balloon")
    private List<WebElement> editButtons;

    @FindBy(css = ".add-balloon-btn")
    private List<WebElement> addBalloonButton;

    @FindBy(css = ".add-man-btn")
    private List<WebElement> addManufacturer;

    public BalloonPage(WebDriver driver) {
        super(driver);
    }

    public static BalloonPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, BalloonPage.class);
    }

    public void assertElemts(int balloonNumber, int deleteButtons, int editButtons, int addButtons, int addMan) {
        Assert.assertEquals("rows do not match", balloonNumber, this.getBalloonRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddBalloonButton().size());
        Assert.assertEquals("add-man is visible", addMan, this.getAddManufacturer().size());
    }
}
