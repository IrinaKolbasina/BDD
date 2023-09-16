package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {

  private SelenideElement heading = $(withText("Пополнение карты"));
  private SelenideElement amount = $("[data-test-id='amount'] input");
  private SelenideElement from = $("[data-test-id=from] input");
  private SelenideElement transferButton = $("[data-test-id=action-transfer");
  private SelenideElement cancelButton = $("[data-test-id=action-cancel]");
  private SelenideElement error = $("[data-test-id=error-notification");

  public MoneyTransferPage() {
    heading.shouldBe(Condition.visible);
  }

  public DashboardPage transferFrom(String sum, DataHelper.CardNumber curdNumber) {
    amount.setValue(sum);
    from.setValue(String.valueOf(curdNumber));
    transferButton.click();
    return new DashboardPage();
  }

  public void getError() {
    $(byText("Ошибка!")).shouldBe(Condition.visible);
  }

  public DashboardPage cancelButton() {
    cancelButton.click();
    return new DashboardPage();
  }

  public MoneyTransferPage clearAmount() {
    amount.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
    return new MoneyTransferPage();
  }

  public MoneyTransferPage clearFrom() {
    from.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
    return new MoneyTransferPage();
  }
}