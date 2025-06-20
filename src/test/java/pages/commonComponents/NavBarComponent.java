package pages.commonComponents;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class NavBarComponent {

    //todo верхний регистр
    private final SelenideElement CART = $(By.xpath("//*[text()='Cart']"));
    private final SelenideElement LOGIN = $("#login2");
    private final SelenideElement LOGOUT = $(By.xpath("//*[text()='Log out']"));
    private final SelenideElement SIGNUP = $("#signin2");
    private final SelenideElement USERNAME_LOGGED = $("#nameofuser");

    //todo сложновато получается, кажется, можно упростить до одного метода при вызове, а не  двух
    // "NAVBAR_COMPONENT.goTo(NAVBAR_COMPONENT.getLogin())"
    public NavBarComponent goTo(SelenideElement element) {
        Allure.step("Go to " + element.toString(), () -> {
            element.shouldBe(visible).click();
        });

        return this;
    }

    public SelenideElement getCart() {
        return CART;
    }

    public SelenideElement getLogin() {
        return LOGIN;
    }

    public SelenideElement getSignUp() {
        return SIGNUP;
    }

    //todo getCart(), но logout() ?
    public SelenideElement logout() {
        return LOGOUT;
    }

    public SelenideElement usernameAfterLogin() {
        return USERNAME_LOGGED;
    }

    //todo поменяла бы название метода
    public void shouldShowWelcome(String name) {
        Allure.step("Check username in Main Page after login", () -> {
            USERNAME_LOGGED.shouldBe(visible).shouldHave(text(name));
        });
    }
}
