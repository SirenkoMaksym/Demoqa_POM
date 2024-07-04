/*
 * created by max$
 */


package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class InterActionsPage extends BasePage {
    public InterActionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "draggable")
    WebElement dragMe;
    @FindBy(id = "droppable")
    WebElement dropHere;

    public InterActionsPage actionDragMe() {
        pause(1000);
        new Actions(driver).dragAndDrop(dragMe, dropHere).perform();
        return this;
    }


    public InterActionsPage verifyDropped(String text) {
        Assert.assertTrue(shouldHaveText(dropHere, text, 5));
        return this;
    }

    public InterActionsPage actionDragMeBy(int x, int y) {
        pause(1000);
        moveWithJS(dragMe, 0, 100);
        //get coordinates DragMe(from) and print
        int xOffset1 = dragMe.getLocation().getX();
        int yOffset1 = dragMe.getLocation().getY();
        System.out.println("dragMe x --> " + xOffset1 + "***" + "y-->" + yOffset1);

        //get coordinates Drop(to) and print
        int xOffset = dropHere.getLocation().getX();
        int yOffset = dropHere.getLocation().getY();
        System.out.println("dropHere x --> " + xOffset + "***" + "y-->" + yOffset);

        //find the difference xOffset and yOffset
        xOffset = (xOffset - xOffset1) + x;
        yOffset = (yOffset - yOffset1) + y;
        new Actions(driver).dragAndDropBy(dragMe, xOffset, yOffset).perform();
        return this;
    }
}
