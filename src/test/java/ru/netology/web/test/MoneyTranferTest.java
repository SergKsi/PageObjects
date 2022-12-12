package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.DataHelper;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataHelper.*;

public class MoneyTranferTest {
    @Test
    void shouldBeTransferMoneyFromCardSecondToFirst() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var balanceCardFirst = dashBoardPage.getBalanceCard(0);
        var balanceCardSecond = dashBoardPage.getBalanceCard(1);
        var summa = genValidSumma(balanceCardSecond);
        var expectedBalanceCardFirst = balanceCardFirst + summa;
        var expectedBalanceCardSecond = balanceCardSecond - summa;
        var tranferPage = dashBoardPage.selectFirstCard();
        dashBoardPage = tranferPage.transferValidCard(String.valueOf(summa), secondCardInfo);
        var actualBalanceCardFirst = dashBoardPage.getBalanceCard(0);
        var actualBalanceCardSecond = dashBoardPage.getBalanceCard(1);
        Assertions.assertEquals(expectedBalanceCardFirst, actualBalanceCardFirst);
        Assertions.assertEquals(expectedBalanceCardSecond, actualBalanceCardSecond);
    }

    @Test
    void shouldBeTransferMoneyFromCardFirstToSecond() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var balanceCardFirst = dashBoardPage.getBalanceCard(0);
        var balanceCardSecond = dashBoardPage.getBalanceCard(1);
        var summa = genValidSumma(balanceCardFirst);
        var expectedBalanceCardFirst = balanceCardFirst - summa;
        var expectedBalanceCardSecond = balanceCardSecond + summa;
        var tranferPage = dashBoardPage.selectSecondCard();
        dashBoardPage = tranferPage.transferValidCard(String.valueOf(summa), firstCardInfo);
        var actualBalanceCardFirst = dashBoardPage.getBalanceCard(0);
        var actualBalanceCardSecond = dashBoardPage.getBalanceCard(1);
        Assertions.assertEquals(expectedBalanceCardFirst, actualBalanceCardFirst);
        Assertions.assertEquals(expectedBalanceCardSecond, actualBalanceCardSecond);
    }

    // перевод с карты при отрицательном остатке
    @Test
    void negativeCashBalanceTest() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var balanceCardFirst = dashBoardPage.getBalanceCard(0);
        var balanceCardSecond = dashBoardPage.getBalanceCard(1);
        var summa = genBadValidSumma(balanceCardFirst);
        var expectedBalanceCardFirst = balanceCardFirst - summa;
        var expectedBalanceCardSecond = balanceCardSecond + summa;
        var tranferPage = dashBoardPage.selectSecondCard();
        tranferPage.transferCard(String.valueOf(summa), firstCardInfo);
        var actualBalanceCardFirst = dashBoardPage.getBalanceCard(0);
        var actualBalanceCardSecond = dashBoardPage.getBalanceCard(1);
        Assertions.assertEquals(expectedBalanceCardFirst, actualBalanceCardFirst);
        Assertions.assertEquals(expectedBalanceCardSecond, actualBalanceCardSecond);
    }
}
