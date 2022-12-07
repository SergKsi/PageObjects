package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.DataHelper;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;
import ru.netology.web.page.YourCardsPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTranferTest {

    @Test
    void shouldBeTransferMoneyFromCardSecondToFirst() {
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(DataHelper.getVerificationCodeFor(authInfo));
        int BalanceCardFirst = DataHelper.getBalanceCard(0);
        int BalanceCardSecond = DataHelper.getBalanceCard(1);
        new YourCardsPage()
                .yourCardsFirstPage();
        var transferData = DataHelper.getTransDataFromFromSecondOnFirst();
        new TransferPage()
                .validTransfer(transferData);
        int actualBalanceCardFirst = BalanceCardFirst + Integer.parseInt(DataHelper.SummaTransfer);
        int actualBalanceCardSecond = BalanceCardSecond - Integer.parseInt(DataHelper.SummaTransfer);
        BalanceCardFirst = DataHelper.getBalanceCard(0);
        BalanceCardSecond = DataHelper.getBalanceCard(1);
        Assertions.assertEquals(BalanceCardFirst, actualBalanceCardFirst);
        Assertions.assertEquals(BalanceCardSecond, actualBalanceCardSecond);
    }

    @Test
    void shouldBeTransferMoneyFromCardFirstToSecond() {
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(DataHelper.getVerificationCodeFor(authInfo));
        int BalanceCardFirst = DataHelper.getBalanceCard(0);
        int BalanceCardSecond = DataHelper.getBalanceCard(1);
        new YourCardsPage()
                .yourCardsSecondPage();
        var transferData = DataHelper.getTransDataFromFromFirstOnSecond();
        new TransferPage()
                .validTransfer(transferData);
        int actualBalanceCardFirst = BalanceCardFirst - Integer.parseInt(DataHelper.SummaTransfer);
        int actualBalanceCardSecond = BalanceCardSecond + Integer.parseInt(DataHelper.SummaTransfer);
        BalanceCardFirst = DataHelper.getBalanceCard(0);
        BalanceCardSecond = DataHelper.getBalanceCard(1);
        Assertions.assertEquals(BalanceCardFirst, actualBalanceCardFirst);
        Assertions.assertEquals(BalanceCardSecond, actualBalanceCardSecond);
    }

    // перевод с карты при отрицательном остатке
    @Test
    void negativeCashBalanceTest() {
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(DataHelper.getVerificationCodeFor(authInfo));
        int BalanceCardFirst = DataHelper.getBalanceCard(0);
        int BalanceCardSecond = DataHelper.getBalanceCard(1);
        new YourCardsPage()
                .yourCardsSecondPage();
        var transferData = DataHelper.getTransDataFromFromFirstOnSecond();
        new TransferPage()
                .noValidTransfer(transferData);
        int newSummaTransfer = 50000;
        int actualBalanceCardFirst = BalanceCardFirst - newSummaTransfer;
        int actualBalanceCardSecond = BalanceCardSecond + newSummaTransfer;
        if (actualBalanceCardFirst < 0) {
            actualBalanceCardFirst = BalanceCardFirst;
            actualBalanceCardSecond = BalanceCardSecond;
        }
        BalanceCardFirst = DataHelper.getBalanceCard(0);
        BalanceCardSecond = DataHelper.getBalanceCard(1);
        Assertions.assertEquals(BalanceCardFirst, actualBalanceCardFirst);
        Assertions.assertEquals(BalanceCardSecond, actualBalanceCardSecond);
    }

}
