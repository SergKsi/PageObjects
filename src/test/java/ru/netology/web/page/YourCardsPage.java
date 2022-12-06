package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YourCardsPage {

    private SelenideElement depositButtonFirst = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] [data-test-id=\"action-deposit\"]");
    private SelenideElement depositButtonSecond = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"] [data-test-id=\"action-deposit\"]");

    public DashBoardPage yourCardsFirstPage() {
        depositButtonFirst.click();
        return new DashBoardPage();
    }

    public DashBoardPage yourCardsSecondPage() {
        depositButtonSecond.click();
        return new DashBoardPage();
    }

}
