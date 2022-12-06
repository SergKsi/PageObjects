package ru.netology.web.data;

import com.codeborne.selenide.ElementsCollection;
import lombok.Value;
import lombok.val;

import static com.codeborne.selenide.Selenide.$$;

public class DataHelper {

    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private static ElementsCollection cards = $$(".list__item");

    private static final String FirstCard = "5559 0000 0000 0001";
    private static final String SecondCard = "5559 0000 0000 0002";

    public static String SummaTransfer = "300";


    // private - из вне никто вызвать не сможет.
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    // данные для успешного ввода
    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }
    // данные для неуспешного ввода
    public static AuthInfo getBadAuthInfo() {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static VerificationCode getBadVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("54321");
    }

    @Value
    public static class TransferData{
        String summa;
        String fromCard;
        String onCard;
    }

    public static TransferData getTransDataFromFromFirstOnSecond() {
        return new TransferData(SummaTransfer, FirstCard , SecondCard);
    }
    public static TransferData getTransDataFromFromSecondOnFirst() {
        return new TransferData(SummaTransfer, SecondCard, FirstCard);
    }

    public static int getBalanceCard(int index) {
        val text = cards.get(index).text();
        return extractBalance(text);
    }
    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}








