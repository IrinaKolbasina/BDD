package ru.netology.web.test;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.MoneyTransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var dashboardPage = new DashboardPage();

        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        var moneyTransferPage = dashboardPage.firstReplenishButton();
        String sum = "1";
        var cardNumber = DataHelper.getSecondCardNumber();
        moneyTransferPage.transferFrom(sum, cardNumber);

        assertEquals(secondCardBalance - Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
        assertEquals(firstCardBalance + Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
    }
}