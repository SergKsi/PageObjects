package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement depositButtonFirst = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] [data-test-id=\"action-deposit\"]");
    private SelenideElement depositButtonSecond = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"] [data-test-id=\"action-deposit\"]");
    private static ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    public DashBoardPage() {
        heading.shouldBe(visible);
    }
    public TransferPage selectFirstCard() {
        depositButtonFirst.click();
        return new TransferPage();
    }
    public TransferPage selectSecondCard() {
        depositButtonSecond.click();
        return new TransferPage();
    }
    public static int getBalanceCard(int index) {
        var text = cards.get(index).text();
        return extractBalance(text);
    }
    private static int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
