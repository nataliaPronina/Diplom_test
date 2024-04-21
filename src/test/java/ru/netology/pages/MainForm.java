package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainForm {
    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement buyButton = $("button['button button_size_m button_theme_alfa-on-white']");
    private SelenideElement buyInCreditButton = $("button['button button_view_extra button_size_m button_theme_alfa-on-white']");


    public MainForm() {
        heading.shouldBe(visible);
    }

    public JustBuyForm orderCardPage() {
        buyButton.click();
        return new JustBuyForm();
    }

    public BuyInCreditForm creditPage() {
        buyInCreditButton.click();
        return new BuyInCreditForm();
    }
}
