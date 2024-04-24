package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.JustBuyForm;
import ru.netology.pages.MainForm;

import java.sql.SQLException;

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


    @Test // N1
    void checkCardApprovedForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getApprovedCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCardRequestStatus());
    }


    @Test //N2 - баг
    void checkCardDeclinedForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getDeclinedCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCardRequestStatus());
    }

    @Test //N3 - баг
    void checkAllNullSymbolsNumberForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getAllNullSymbolsNumberCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getCardRequestStatus());
    }
    @Test //N4
    void checkInvalidCardNumberForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomInvalidCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N5
    void checkInvalidNullMonthForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomInvalidMonthNullSymbol();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N6
    void checkInvalidMonthForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomInvalidMonth();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }


    @Test // N7
    void checkInvalidPastMonthForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomInvalidPastMonth();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test //N8
    void checkInvalidPastYearForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomInvalidPastYear();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkCardExpiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N9
    void checkInvalidFutureYearForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomInvalidFutureYear();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test //N10 - баг
    void checkRandomOnlyFirstNameForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomOnlyFirstName();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N11 - баг
    void checkRandomOnlyLastNameForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomOnlyLastName();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N12 - баг
    void checkRussianNameForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomRussianName();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test //N13
    void checkInvalidCvcTwoSymbolsForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getRandomInvalidCvc();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N14
    void checkCardNumberEmptyFieldForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getEmptyCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test //N15
    void checkMonthEmptyFieldForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getMonthEmptyFieldCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N16
    void checkYearEmptyFieldForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getYearEmptyFieldCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test //N17
    void checkOwnerEmptyFieldForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getOwnerEmptyFieldCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkFieldRequiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test //N18
    void checkCvcEmptyFieldForCard() {
        mainForm.cardForm();
        var cardInfo = DataHelper.getCvcEmptyFieldCard();
        var buyByCardPage = new JustBuyForm();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}
