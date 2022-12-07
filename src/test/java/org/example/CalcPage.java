package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.Arrays;

public class CalcPage {
    /**
     * конструктор класса, инициализацией полей класса
     */
    public WebDriver driver;

    public CalcPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
     * определение локатора dropdown меню
     */
    @FindBy(id="region_id")
    WebElement status_dropdown;

    /**
     * метод выбора локации в dropdown
     */
    public void select_list(int value){
        Select statusDropdown=new Select(status_dropdown);
        statusDropdown.selectByIndex(value);
    }

    /**
     * метод очистки поля ввода
     */
    public void clearField(){ powerField.clear(); }

    /**
     * определение локатора поля ввода
     */
    @FindBy(xpath = "//*[contains(@id, 'power')]")
    private WebElement powerField;

    /**
     * метод для ввода значения
     */
    public void inputValue(String num) {
        powerField.sendKeys(num);
    }

    /**
     * определение локатора кнопки
     */
    @FindBy(xpath = "//*[contains(text(), 'Рассчитать налог')]/..")
    private WebElement loginBtn;

    /**
     * метод для нажатия кнопки
     */
    public void clickBtn() { loginBtn.click(); }

    /**
     * определение локатора результата расчета
     */
    @FindBy(css = "#tax > strong")
    private WebElement resValue;

    /**
     * метод для получения результата расчета
     */
    public String getResultValue() {
        String resVal = resValue.getText().replaceAll("\\D+","");
        return resVal;
    }

    /**
     * определение локатора сообщения об ошибке
     */
    @FindBy(xpath = "//*[contains(@id, 'calc_error')]")
    WebElement errorMessage;

    /**
     * метод для проверки наличия класса
     */
    public boolean getClassNameNotification(String active) {
        return Arrays.asList(errorMessage.getAttribute("class").split(" ")).contains(active);
    }
}