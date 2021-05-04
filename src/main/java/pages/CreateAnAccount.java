package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAnAccount extends BasePage{
    public CreateAnAccount() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//label[@class='radio-inline'][2]")
    private WebElement socialTitleMrsRadioButton;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastNameField;

    @FindBy(xpath = "//div[@class='col-md-6']//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name='birthday']")
    private WebElement birthdayField;

    @FindBy(xpath = "//input[@name='customer_privacy']")
    private WebElement customerDataPrivacyCheckbox;

    @FindBy(xpath = "//input[@name='psgdpr']")
    private WebElement termsAndConditionsCheckbox;

    @FindBy(xpath = "//footer/button")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@class='help-block']//li")
    private WebElement invalidFormatMessage;




    public CreateAnAccount selectSocialTitle() {
        socialTitleMrsRadioButton.click();
        return this;
    }

    public CreateAnAccount fillFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    public CreateAnAccount fillLastName(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    public CreateAnAccount fillEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public CreateAnAccount fillPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public CreateAnAccount fillBirthday(String birthday) {
        birthdayField.sendKeys(birthday);
        return this;
    }

    public CreateAnAccount checkCustomerDataPrivacy() {
        customerDataPrivacyCheckbox.click();
        return this;
    }

    public CreateAnAccount checkTermsAndConditions() {
        termsAndConditionsCheckbox.click();
        return this;
    }

    public MainPage submit() {
        saveButton.click();
        return new MainPage();
    }

    public String submitWithNotValidData() {
        saveButton.click();
        String css = firstNameField.getCssValue("outline-color");
        String errorMessage = invalidFormatMessage.getText();
        String result = errorMessage + " " + css;
        return result;
    }

    /*public String searchErrorMessage() {
        String errorMessage = invalidFormatMessage.getText();
        return errorMessage;
    }*/









}
