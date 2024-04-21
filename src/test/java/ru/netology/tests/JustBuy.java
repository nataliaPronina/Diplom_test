package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.JustBuyForm;
import ru.netology.pages.MainForm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JustBuy {

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
    void checkCardApprovedForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getApprovedCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCardRequestStatus());
    }


    @Test
    void checkCardDeclinedForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getDeclinedCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCardRequestStatus());
    }

    @Test
    void checkAllFieldEmptyForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getEmptyCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkErrorNotificationFourFields();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void checkInvalidCardNumberForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getRandomInvalidCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void checkRandomValidCardForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getRandomValidCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkErrorNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void checkInvalidMonthForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getRandomInvalidMonth();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void checkInvalidNullMonthForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getRandomInvalidMonthNullSymbol();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void checkInvalidYearForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getRandomInvalidYear();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkCardExpiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }



    @Test
    void checkInvalidCvcForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getRandomInvalidCvc();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }


    @Test
    void checkEmptyFieldForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getOwnerEmptyFieldCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkFieldRequiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void checkFullNameForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getRandomFullName();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }


    @Test
    void checkRussianNameForCard() {
        mainForm.orderCardPage();
        var cardInfo = DataHelper.getRandomRussianName();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }



}
