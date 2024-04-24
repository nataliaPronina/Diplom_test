package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainForm {
    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement buyButton = $(byText("Купить"));
    private SelenideElement buyInCreditButton = $(byText("Купить в кредит"));


    public MainForm() {
        heading.shouldBe(visible);
    }

    public JustBuyForm cardForm() {
        buyButton.click();
        return new JustBuyForm();
    }

    public BuyInCreditForm creditForm () {
        buyInCreditButton.click();
        return new BuyInCreditForm();
    }
}
