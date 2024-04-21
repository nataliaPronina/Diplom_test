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

    @Test
    void checkCardApprovedForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getApprovedCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }


    @Test
    void checkCardDeclinedForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getDeclinedCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCardRequestStatus());
    }


    @Test
    void checkInvalidCardNumberForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getRandomInvalidCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void checkRandomValidCardForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getRandomValidCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkErrorNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void checkInvalidMonthForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getRandomInvalidMonth();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }



    @Test
    void checkInvalidYearForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getRandomInvalidYear();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkCardExpiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }



    @Test
    void checkInvalidCvcForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getRandomInvalidCvc();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }


    @Test
    void checkEmptyFieldForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getOwnerEmptyFieldCard();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkFieldRequiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }


    @Test
    void checkFullNameForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getRandomFullName();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }


    @Test
    void checkRussianNameForCredit() {
        mainForm.creditPage();
        var cardInfo = DataHelper.getRandomRussianName();
        var creditPage = new BuyInCreditForm();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }


}
