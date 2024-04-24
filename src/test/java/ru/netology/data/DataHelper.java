package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {

    }

    public static CardInfo getApprovedCard() {
        return new CardInfo("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444444444444442", generateCurrentMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static CardInfo getEmptyCard() {
        return new CardInfo("", generateCurrentMonth(), generateCurrentYear(),
                generateName("en"), generateValidCvc());
    }

    public static CardInfo getAllNullSymbolsNumberCard() {
        return new CardInfo("0000000000000000", generateCurrentMonth(), generateCurrentYear(),
                generateName("en"), generateValidCvc());
    }
    public static CardInfo getCvcEmptyFieldCard() {
        return new CardInfo("4444444444444441", generateCurrentMonth(), generateCurrentYear(),
                generateName("en"), "");
    }


    public static CardInfo getRandomInvalidCard() {
        return new CardInfo(generateInvalidCardNumber(), generateCurrentMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static CardInfo getRandomInvalidPastMonth() {
        return new CardInfo("4444444444444441", generatePastMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static CardInfo getRandomInvalidMonthNullSymbol() {
        return new CardInfo("4444444444444441", generateInvalidNullMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static CardInfo getRandomInvalidMonth() {
        return new CardInfo("4444444444444441", generateInvalidMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }
    public static CardInfo getRandomInvalidPastYear() {
        return new CardInfo("4444444444444441", generateCurrentMonth(),
                generatePastYear(), generateName("en"), generateValidCvc());
    }
    public static CardInfo getRandomInvalidFutureYear() {
        return new CardInfo("4444444444444441", generateCurrentMonth(),
                generateFutureYear(), generateName("en"), generateValidCvc());
    }


    public static CardInfo getRandomFullName() {
        return new CardInfo("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateFullName("en"), generateValidCvc());
    }

    public static CardInfo getRandomRussianName() {
        return new CardInfo("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateName("ru"), generateValidCvc());
    }

    public static CardInfo getRandomOnlyFirstName() {
        return new CardInfo("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateOnlyFirstName("en"), generateValidCvc());
    }
    public static CardInfo getRandomOnlyLastName() {
        return new CardInfo("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateOnlyLastName("en"), generateValidCvc());
    }
    public static CardInfo getRandomInvalidCvc() {
        return new CardInfo("4444444444444441", generateRandomMonth(),
                generateFutureYear(), generateName("en"), generateInvalidCvc());
    }

    public static CardInfo getRandomInvalidCvcTwoSymbols() {
        return new CardInfo("4444444444444441", generateRandomMonth(),
                generateFutureYear(), generateName("en"), generateFutureYear());
    }

    public static CardInfo getOwnerEmptyFieldCard() {
        return new CardInfo("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), "", generateValidCvc());
    }
    public static CardInfo getMonthEmptyFieldCard() {
        return new CardInfo("4444444444444441", "",
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static CardInfo getYearEmptyFieldCard() {
        return new CardInfo("4444444444444441", generateCurrentMonth(),
                "", generateName("en"), generateValidCvc());
    }
    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateFullName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static String generateOnlyFirstName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName();
    }

    public static String generateOnlyLastName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName();
    }
    public static String generateValidCardNumber() {
        Faker faker = new Faker();
        return faker.numerify("################");
    }

    public static String generateInvalidCardNumber() {
        Faker faker = new Faker();
        return faker.numerify("###############");
    }

    public static String generateRandomMonth() {
        int month = (int)((Math.random() * 12) + 1);;
        return String.format("%02d", month);

    }
// minValue + Math.random() * (maxValue - minValue + 1)

    public static String generateInvalidMonth() {
        int month = (int) (13 + Math.random() * (99 - 13 + 1));
        return String.format("%02d", month);
    }


    public static String generatePastMonth() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue() - 1;
        return String.format("%02d", month);
    }

    public static String generateInvalidNullMonth() {
        return ("00");
    }



    public static String generateCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear() - 2000;
        return Integer.toString(year);
    }

    public static String generateCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateFutureYear() {
        Random random = new Random();
        int i = random.nextInt(5) + 5;
        LocalDate futureDate = LocalDate.now().plusYears(i);
        int year = futureDate.getYear() - 2000;
        return Integer.toString(year);
    }

        public static String generatePastYear() {
        int pastYear = (int)((Math.random() * 24) - 1);;
        return String.format("%02d", pastYear);
    }


    public static String generateValidCvc() {
        Faker faker = new Faker();
        return faker.numerify("###");
    }

    public static String generateInvalidCvc() {
        Faker faker = new Faker();
        return faker.numerify("##");
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String cardHolder;
        String cvc;

    }
}
