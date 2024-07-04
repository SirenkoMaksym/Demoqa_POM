/*
 * created by max$
 */


package com.demoqa.tests.elements;

import com.demoqa.pages.ElementsPage;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaScriptTest extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(driver).getElements();
        new SidePanel(driver).selectContexBox();
    }

    @Test
    public void JSExecutorTest() {
        new ElementsPage(driver).enterDataWithJS("Bob Merkel", "bob@gmail.com")
                .clickOnSubmitButtonWithJS()
                .getInnerTextWithJS()
                .refreshWithJS()
                .getInnerTextWithJS()
                .checkURLWithJS()
                .navigateToNewPage("https://ticket-service-69443.firebaseapp.com")
                .verifyTitleOfPageWithJS()
                .generateAlertWithJS();
    }
}
