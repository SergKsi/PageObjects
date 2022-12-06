package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement summaTransfer = $("[data-test-id=\"amount\"] input");
    private SelenideElement fromCard = $("[data-test-id=\"from\"] input");
//    private SelenideElement onCard = $("[data-test-id=\"to\"] input");
    private SelenideElement transferButton = $("[data-test-id=\"action-transfer\"]");

    public TransferPage() {
        summaTransfer.shouldBe(visible);
        fromCard.shouldBe(visible);
    }

    public YourCardsPage validTransfer(DataHelper.TransferData transferData) {
        summaTransfer.setValue(transferData.getSumma());
        fromCard.setValue(transferData.getFromCard());
//        onCard.setValue(transferData.getOnCard());
        transferButton.click();
        return new YourCardsPage();
    }

}
