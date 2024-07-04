/*
 * created by max$
 */


package com.demoqa.pages;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

public class ElementsPage extends BasePage {
    JavascriptExecutor js;

    public ElementsPage(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;
    }


    @FindBy(id = "doubleClickBtn")
    WebElement doubleClickBtn;

    public ElementsPage doubleClick() {
        pause(1000);
        new Actions(driver).doubleClick(doubleClickBtn).perform();
        return this;
    }

    @FindBy(id = "doubleClickMessage")
    WebElement doubleClickMessage;

    public ElementsPage verifyDoubleClick(String text) {
        Assert.assertTrue(shouldHaveText(doubleClickMessage, text, 5));
        return this;
    }


    @FindBy(id = "rightClickBtn")
    WebElement rightClickBtn;

    public ElementsPage rightClick() {
        pause(1000);
        new Actions(driver).contextClick(rightClickBtn).perform();
        return this;
    }

    @FindBy(id = "rightClickMessage")
    WebElement rightClickMessage;

    public ElementsPage verifyRightClick(String text) {
        Assert.assertTrue(shouldHaveText(rightClickMessage, text, 5));
        return this;
    }

    @FindBy(id = "uploadFile")
    WebElement uploadFile;

    public ElementsPage performKeyEventsWithRobot() {
        pause(1000);
        //    clickWithJS(uploadFile,0,200);
        moveWithJS(uploadFile, 0, 200);
        clickWithRectangle(uploadFile, 10, 3);

        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        //press SHIFT
        robot.keyPress(KeyEvent.VK_SHIFT);
        pause(1000);
        //press D(upperCase)
        robot.keyPress(KeyEvent.VK_D);
        //release
        robot.keyRelease(KeyEvent.VK_SHIFT);
        //press 1,.,t,x,t
        robot.keyPress(KeyEvent.VK_1);
        robot.keyPress(KeyEvent.VK_PERIOD);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyPress(KeyEvent.VK_T);
        //pres ENTER KEY
        robot.keyPress(KeyEvent.VK_ENTER);

        return this;
    }

    @FindBy(id = "uploadedFilePath")
    WebElement uploadedFilePath;

    public ElementsPage verifyPath(String path) {
        Assert.assertTrue(shouldHaveText(uploadedFilePath, path, 5));
        return this;
    }

    public ElementsPage performMouseEvent() {
        pause(1000);
        //    clickWithJS(uploadFile,0,200);
        moveWithJS(uploadFile, 0, 200);
        clickWithRectangle(uploadFile, 10, 3);

        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        //find x and y coordinates to mouseMove method
        //get window size
        Dimension dimension = driver.manage().window().getSize();
        System.out.println("Dimension x and y " + dimension.getWidth() + "***" + dimension.getHeight());
        //get point of file location
        int x = dimension.getWidth() / 2-260;
        int y = dimension.getHeight() / 2+150;
        robot.mouseMove(x, y);
        pause(1000);
        //click left mouse button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        //realise left mouse button
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        //press enter key
        robot.keyPress(KeyEvent.VK_ENTER);

        return this;
    }

    public ElementsPage enterDataWithJS(String name, String email) {
        js.executeScript("document.getElementById('userName').value=' " + name + " ';");
        js.executeScript("document.getElementById('userName').style.border='5px solid red';");
        js.executeScript("document.getElementById('userEmail').value=' " + email + " ';");
        js.executeScript("document.getElementById('userEmail').style.border='5px solid green';");


        return this;
    }

    public ElementsPage clickOnSubmitButtonWithJS() {
        js.executeScript("document.querySelector('#submit').style.backgroundColor='Red';");
        js.executeScript("document.querySelector('#submit').click();");
        return this;
    }

    public ElementsPage getInnerTextWithJS() {
        pause(1000);
        String innerText = js.executeScript("return document.documentElement.innerText;").toString();
        System.out.println(innerText);
        System.out.println("================================================================");
        return this;
    }

    public ElementsPage refreshWithJS() {
        js.executeScript("history.go(0);");
        return this;
    }

    public ElementsPage checkURLWithJS() {
        String url = js.executeScript("return document.URL;").toString();
        System.out.println("URL --> " + url);
        System.out.println("================================================================");
        return this;
    }

    public ElementsPage navigateToNewPage(String url) {
        js.executeScript("window.location= ' " + url + "';");
        return this;
    }

    public ElementsPage verifyTitleOfPageWithJS() {
        String title = js.executeScript("return document.title;").toString();
        System.out.println("Title of the page is " + title);
        System.out.println("================================================================");
        pause(1000);
        return this;
    }

    public ElementsPage generateAlertWithJS() {
        pause(1000);
        js.executeScript("alert('Hello world');");
      //  pause(2000);
       // js.executeScript("window.confirm=function(){return true;}");
        return this;
    }

    @FindBy(css = "a")
    List<WebElement> allLinks;

    public ElementsPage checkAllURL() {
        String url = "";
        System.out.println("total links on the page: " + allLinks.size());
        Iterator<WebElement> iterator = allLinks.iterator();
        while (iterator.hasNext()) {
            url = iterator.next().getText();
            System.out.println(url);
        }
        return this;
    }

    public ElementsPage checkBrokenLinks() {
        for (int i = 0; i < allLinks.size(); i++) {
            WebElement element = allLinks.get(i);
            String url = element.getAttribute("href");
            verifyLinks(url);
        }
        return this;
    }

    @FindBy(css="img")
List<WebElement> images;
    public ElementsPage checkBrokenImages() {
        System.out.println("Total images on the web page: " + images.size());

        for (int i = 0; i < images.size(); i++) {
            WebElement image = images.get(i);
            String imageUrl = image.getAttribute("src");
            verifyLinks(imageUrl);
            try {
                boolean imageDisplay = (Boolean)((JavascriptExecutor)driver)
                        .executeScript("return (typeof arguments[0].naturalWidth != undefined && arguments[0].naturalWidth>0);",image);
                if (imageDisplay){
                    System.out.println("DISPLAY - OK");
                    System.out.println("=========================================================");
                }else{
                    System.out.println("DISPLAY - BROKEN");
                    System.out.println("=========================================================");
                }
            } catch (Exception e) {
                System.out.println("ERROR occurred");;
            }
        }

        return this;
    }
@FindBy(id = "userName")
WebElement userName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    public ElementsPage enterPersonalData(String name, String email) {
        moveWithJS(userName,0,300);
        type(userName, name);
        type(userEmail,email);
        return this;

    }
@FindBy(id = "currentAddress")
WebElement currentAddress;
    public ElementsPage keyBoardEvent(String address) {
        type(currentAddress,address);
        Actions actions = new Actions(driver);

        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        actions.sendKeys(Keys.TAB).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        pause(1000);
        return this;
    }
@FindBy(id = "submit")
WebElement submit;

    public ElementsPage submit() {
        clickWithJS(submit,0,200);
        return this;
    }
@FindBy(css=".border #currentAddress")
WebElement currentAddressResult;
    @FindBy(css = ".border #permanentAddress")
    WebElement permanentAddressResult;
    public ElementsPage verifyCopyPastText() {
        String[] current = currentAddressResult.getText().split(":");

        String[] permanent = permanentAddressResult.getText().split(":");
        Assert.assertEquals(permanent[1],current[1]);
        return this;
    }
}
