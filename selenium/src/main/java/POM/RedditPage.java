package POM;

import Utilities.Utilities;
import io.cucumber.java.sl.In;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class RedditPage extends Utilities {

    public RedditPage() {
        driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    public static String amount;
    public static String publicLink;
    String strDate;
    public static String url;

    @FindBy(xpath = ".//a[text()='Browse Reddit']/parent::div/a[text()='Sign Up']")
    public WebElement _SignUpButton;

    @FindBy(id = "loginUsername")
    public WebElement _userName;

    @FindBy(id = "loginPassword")
    public WebElement _password;

    @FindBy(xpath = ".//button[contains(text(),'Log In')][@type='submit']")
    public WebElement _logInButton;

    @FindBy(xpath = "//a[text()='Create Post']")
    public WebElement _createPost;

    @FindBy(xpath = ".//textarea[@placeholder='Title']")
    public WebElement _postTitle;

    @FindBy(xpath = ".//button[text()='Post'][@tabindex='0']")
    public WebElement _postButton;

    @FindBy(xpath = ".//button[text()='Comment'][@tabindex='0']")
    public WebElement _commentButton;

    @FindBy(xpath = ".//div[@class='notranslate public-DraftEditor-content']")
    public WebElement _commentEditorBox;

    @FindBy(xpath = ".//div[@class='notranslate public-DraftEditor-content']")
    public WebElement _verifyComment;

    @FindBy(xpath = ".//div[@data-test-id='comment']/following-sibling::div//button[@aria-label='upvote']/parent::div/div[text()='1']")
    public WebElement _upvote;

    @FindBy(xpath = ".//div[@data-test-id='comment']/following-sibling::div//button[@aria-label='downvote']//i")
    public WebElement _downvoteButton;

    @FindBy(xpath = ".//div[@data-test-id='comment']/following-sibling::div//button[@aria-label='downvote']/parent::div/div[text()='-1']")
    public WebElement _downvote;

    public void logInToReddit(String userName, String password) throws InterruptedException {
        Thread.sleep(5000);
        _SignUpButton.click();
        _userName.sendKeys(userName);
        _password.sendKeys(password);
        _logInButton.click();
    }

    public boolean createPost() throws InterruptedException {
        Thread.sleep(8000);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        strDate= formatter.format(date) + " vinayak";
        _createPost.click();
        Thread.sleep(2000);
        _postTitle.click();
        _postTitle.sendKeys(strDate);
        _postButton.click();
        Thread.sleep(3000);
        url = driver.getCurrentUrl();
        return driver.getCurrentUrl().contains("vinayak");
    }

    public boolean navigateToPost() throws InterruptedException {
        driver.get(url);
        return driver.getCurrentUrl().contains("vinayak");
    }

    public boolean commentOnPost() throws InterruptedException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        strDate= formatter.format(date) + " vinayak";
        _commentEditorBox.click();
        _commentEditorBox.sendKeys(strDate);
        _commentButton.click();
        Thread.sleep(2000);
        return _upvote.isDisplayed();
    }

    public boolean downVotingOnPost() throws InterruptedException {
        _downvoteButton.click();
        return _downvote.isDisplayed();
    }
}
