package com.demoqa.tests.forms;

import com.demoqa.pages.FormsPage;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class StudentFormTest extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(driver).getForms();
        new SidePanel(driver).selectPracticeForm().hideIframes();
    }

    @Test
    @Parameters({"firstName","lastName","email","phone"})
    public void createStudentAccountPositiveTest(String firstName, String lastName, String email, String phone) {
        new FormsPage(driver).enterPersonalData(firstName,lastName,email,phone)
                .selectGender("Male")
                //   .enterDate("16 Aug 1987")
                .selectDate("August","1997","16")
                .selectSubjects(new String[]{"Maths","Chemistry"})
                .selectHobbies(new String[]{"Sports","Reading"})
                .uploadFile("C:/Tools/1.jfif")
                .selectState("NCR")
                .selectCity("Delhi")
                .submit()
                .verifySuccessRegistration("Thanks for submitting the form")
        ;
    }
}
