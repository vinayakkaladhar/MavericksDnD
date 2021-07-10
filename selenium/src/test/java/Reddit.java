import POM.RedditPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(Utilities.ListenerTest.class)

public class Reddit extends RedditPage {
    public RedditPage redditPage;
    boolean result=true;

    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        redditPage = new RedditPage();
        Reporter.log("Chrome Browser Launched");

    }

    @Test(description = "Navigation to reddit.com and create a post" , priority = 0)
    public void verifyRedditPostFunctionality() throws InterruptedException, IOException, InvalidFormatException {
        driver.get("https://www.reddit.com/r/MavericksDnD/");
        Reporter.log("Navigated to reddit.com");
        redditPage.logInToReddit("mavdabbler","Y)CKdt7jWg24a.q8");
        Reporter.log("Logged in as : mavdabbler");
        Assert.assertTrue(result, "logged in as : mavdabbler");
        Reporter.log("Create Post option is available");
        result = result && redditPage.createPost();
        Assert.assertTrue(result, "post created");
        Reporter.log("Post was created and verified");
    }

    @Test(description = "Commenting on a post and verify voting functionality" , priority = 1)
    public void verifyCommentingAndVotingOnAPost() throws InterruptedException, IOException, InvalidFormatException {
        result = redditPage.navigateToPost();
        Reporter.log("Navigated to the created post");
        Assert.assertTrue(result, "navigated to the created post");
        result = result && redditPage.commentOnPost();
        Assert.assertTrue(result, "commented on post");
        Reporter.log("Successfully commented on Post");
        Reporter.log("Comment is Upvoted and verified");
        result = result && redditPage.downVotingOnPost();
        Reporter.log("Comment is downvoted and verified");
    }

    @AfterMethod
    public void closeDriver() {

    }
}