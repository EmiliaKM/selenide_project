package pages.loginpage;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

import decorators.CustomAlert;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import pages.BasePage;
import constants.AlertTypes;
import pages.commonComponents.NavBarComponent;
import decorators.SlowType;
import valueObjects.User;

public class LoginPageOptionA extends BasePage<LoginPageOptionA> implements LoginPage {

    //todo верхний регистр
    private final SelenideElement USERNAME_FIELD = $("#loginusername");
    private final SelenideElement PASSWORD_FIELD = $("#loginpassword");
    private final SelenideElement CONFIRM_BUTTON = $(By.xpath("//*[@onclick='logIn()']"));
    private final SelenideElement LOGIN_LABEL = $("#logInModalLabel");
    private final SelenideElement MODAL = $("#logInModal .modal-body");
    private final SelenideElement CLOSE_MODAL = $("div#logInModal .modal-footer button:nth-of-type(1)");
    private final NavBarComponent NAVBAR_COMPONENT = new NavBarComponent();
    //todo с учетои комментариев в SlowType должно стать не нужно
    private final SlowType TYPE = new SlowType();

    @Override
    protected void load() {
        Allure.step("Open main page and navigate to login modal", () ->
                NAVBAR_COMPONENT.goTo(NAVBAR_COMPONENT.getLogin())
        );
    }

    @Override
    protected void isLoaded() {
        Allure.step("Check that login modal is visible", () -> {
            LOGIN_LABEL.shouldBe(visible);
            USERNAME_FIELD.shouldBe(visible);
            PASSWORD_FIELD.shouldBe(visible);
            CLOSE_MODAL.shouldBe(visible);
        });
    }

    @Override
    public LoginPageOptionA login(User user) {
        Allure.step("Fill login and password", () -> {
            TYPE.slowType(getUsernameField(), user.getUsername());
            TYPE.slowType(getPasswordField(), user.getPassword());
        });

        //todo а зачем тут геттер использовать?
        Allure.step("Submit login form", () -> getConfirmButton().click());
        return this;
    }

    @Override
    public void wrongLogin(User user, AlertTypes expectedAlert) {
        login(user);
        Allure.step("Check alert after failed login", () -> {
            //todo не согласна, что тут проверка соответствует описанию, написала в CustomAlert
            CustomAlert alert = new CustomAlert(expectedAlert);
            alert.accept();
        });
    }

    //todo кажется, что геттеры SelenideElement для степов это слишком
    @Override
    public SelenideElement getModal() {
        return MODAL;
    }

    @Override
    public SelenideElement getUsernameField() {
        return USERNAME_FIELD;
    }

    @Override
    public SelenideElement getPasswordField() {
        return PASSWORD_FIELD;
    }

    @Override
    public SelenideElement getConfirmButton() {
        return CONFIRM_BUTTON;
    }

    @Override
    public LoginPageOptionA get() {
        return super.get();
    }
}

