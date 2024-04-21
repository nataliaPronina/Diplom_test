package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BuyInCreditForm {

    private SelenideElement buyInCreditHeading = $(byText("Кредит по данным карты"));

    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonthField = $("[placeholder='08']");
    private SelenideElement cardYearField = $("[placeholder='22']");
    private SelenideElement cardOwnerField = $(byText("Владелец"));
    private SelenideElement cvcCodeField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");
    private SelenideElement wrongFormatNotification = $(byText("Неверный формат"));
    private SelenideElement wrongFormatNotificationElement = $(byText("Неверный формат"));
    private SelenideElement wrongExpiryDateNotification = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredNotification = $(byText("Истёк срок действия карты"));
    private SelenideElement fieldRequiredNotification = $(byText("Поле обязательно для заполнения"));

    public BuyInCreditForm() {
        buyInCreditHeading.shouldBe(visible);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void checkSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void fillInCardData(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        cardMonthField.setValue(cardInfo.getMonth());
        cardYearField.setValue(cardInfo.getYear());
        cardOwnerField.setValue(cardInfo.getCardHolder());
        cvcCodeField.setValue(cardInfo.getCvc());
        clickContinueButton();
    }


    public void checkErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkWrongFormat() {
        wrongFormatNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkWrongExpiryDateNotification() {
        wrongExpiryDateNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkCardExpiredNotification() {
        cardExpiredNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkFieldRequiredNotification() {
        fieldRequiredNotification.shouldBe(visible, Duration.ofSeconds(15));
    }
}

