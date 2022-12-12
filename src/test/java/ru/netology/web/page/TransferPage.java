package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement summaTransfer = $("[data-test-id=\"amount\"] input");
    private SelenideElement fromCard = $("[data-test-id=\"from\"] input");
    private SelenideElement transferButton = $("[data-test-id=\"action-transfer\"]");

    private SelenideElement trasferHeader = $(byText("Пополнение карты"));

    private SelenideElement errorMessage = $("[data-test-id=\"error-message\"]");

    public TransferPage() {
        trasferHeader.shouldBe(visible);
        summaTransfer.shouldBe(visible);
    }

    public DashBoardPage transferValidCard(String summaToTransfer, DataHelper.CardInfo cardInfo) {
        transferCard(summaToTransfer, cardInfo);
        return new DashBoardPage();
    }

    public void transferCard(String summaToTransfer, DataHelper.CardInfo cardInfo) {
        summaTransfer.setValue(summaToTransfer);
        fromCard.setValue(cardInfo.getCardNum());
        transferButton.click();
    }
}
