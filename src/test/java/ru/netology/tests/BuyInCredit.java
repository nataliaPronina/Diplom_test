package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.BuyInCreditForm;
import ru.netology.pages.JustBuyForm;
import ru.netology.pages.MainForm;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyInCredit {

    MainForm mainForm = open("http://localhost:8080/", MainForm.class);

    @AfterEach
    public void cleanBase() throws SQLException {
        SQLHelper.cleanDatabase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test //N19
    void checkCardApprovedForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getApprovedCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }


    @Test //N20
    void checkCardDeclinedForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getDeclinedCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCardRequestStatus());
    }

    @Test //N21 - баг
    void checkAllNullSymbolsNumberForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getAllNullSymbolsNumberCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getCardRequestStatus());
    }
    @Test //N22
    void checkInvalidCardNumberForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomInvalidCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N23
    void checkInvalidNullMonthForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomInvalidMonthNullSymbol();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N24
    void checkInvalidMonthForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomInvalidMonth();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test // N25
    void checkInvalidPastMonthForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomInvalidPastMonth();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }


    @Test //N26
    void checkInvalidPastYearForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomInvalidPastYear();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkCardExpiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N27
    void checkInvalidFutureYearForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomInvalidFutureYear();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N28 - баг
    void checkRandomOnlyFirstNameForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomOnlyFirstName();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N29 - баг
    void checkRandomOnlyLastNameForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomOnlyLastName();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N30 баг
    void checkRussianNameForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomRussianName();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test //N31
    void checkInvalidCvcTwoSymbolsForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getRandomInvalidCvc();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N32
    void checkCardNumberEmptyFieldForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getEmptyCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N33
    void checkMonthEmptyFieldForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getMonthEmptyFieldCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N34
    void checkYearEmptyFieldForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getYearEmptyFieldCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test //N35
    void checkOwnerEmptyFieldForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getOwnerEmptyFieldCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkFieldRequiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test //N36
    void checkCvcEmptyFieldForCredit() {
        mainForm.creditForm();
        var cardInfo = DataHelper.getCvcEmptyFieldCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}
