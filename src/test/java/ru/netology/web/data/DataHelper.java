package ru.netology.web.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    private static final String firstCard = "5559 0000 0000 0001";
    private static final String secondCard = "5559 0000 0000 0002";
    public static int initialBalance = 10000;

    // генерация и подготовка данных.
    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class CardInfo {
        String cardNum;  // 5559 0000 0000 000X
        String nameBank; // 5559 - АльфаБанк
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getBadAuthInfo() {
        return new AuthInfo("petya", "123qwerty");
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static VerificationCode getBadVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("54321");
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo(firstCard, "AlfaBank");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo(secondCard, "AlfaBank");
    }

    public static int genValidSumma(int value) {
        return new Random().nextInt(value) + 1;
    }

    public static int genBadValidSumma(int value) {
        return Math.abs(value) + new Random().nextInt(initialBalance);
    }
}








