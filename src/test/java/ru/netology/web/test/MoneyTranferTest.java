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
        int BalanceCardSecond= DataHelper.getBalanceCard(1);
        new YourCardsPage()
                .yourCardsFirstPage();
        var transferData = DataHelper.getTransDataFromFromSecondOnFirst();
        new TransferPage()
                .validTransfer(transferData);
        int actualBalanceCardFirst = BalanceCardFirst + Integer.parseInt(DataHelper.SummaTransfer);
        int actualBalanceCardSecond = BalanceCardSecond - Integer.parseInt(DataHelper.SummaTransfer);
        BalanceCardFirst = DataHelper.getBalanceCard(0);
        BalanceCardSecond= DataHelper.getBalanceCard(1);
        Assertions.assertEquals(actualBalanceCardFirst,BalanceCardFirst);
        Assertions.assertEquals(actualBalanceCardSecond,BalanceCardSecond);
    }

    @Test
    void shouldBeTransferMoneyFromCardFirstToSecond() {
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(DataHelper.getVerificationCodeFor(authInfo));
        int BalanceCardFirst = DataHelper.getBalanceCard(0);
        int BalanceCardSecond= DataHelper.getBalanceCard(1);
        new YourCardsPage()
                .yourCardsSecondPage();
        var transferData = DataHelper.getTransDataFromFromFirstOnSecond();
        new TransferPage()
                .validTransfer(transferData);
        int actualBalanceCardFirst = BalanceCardFirst - Integer.parseInt(DataHelper.SummaTransfer);
        int actualBalanceCardSecond = BalanceCardSecond + Integer.parseInt(DataHelper.SummaTransfer);
        BalanceCardFirst = DataHelper.getBalanceCard(0);
        BalanceCardSecond= DataHelper.getBalanceCard(1);
        Assertions.assertEquals(actualBalanceCardFirst,BalanceCardFirst);
        Assertions.assertEquals(actualBalanceCardSecond,BalanceCardSecond);
    }

}
