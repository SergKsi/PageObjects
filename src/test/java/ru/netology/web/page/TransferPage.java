package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
public class TransferPage {
    private SelenideElement summaTransfer = $("[data-test-id=\"amount\"] input");
    private SelenideElement fromCard = $("[data-test-id=\"from\"] input");
    private SelenideElement transferButton = $("[data-test-id=\"action-transfer\"]");
    public TransferPage() {
        summaTransfer.shouldBe(visible);
    }
    public DashBoardPage transferCard(String summaToTransfer, DataHelper.CardInfo cardInfo) {
        summaTransfer.setValue(summaToTransfer);
        fromCard.setValue(cardInfo.getCardNum());
        transferButton.click();
        return new DashBoardPage();
    }
}
