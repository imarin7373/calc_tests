package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class CalcTest {
    public static CalcPage calcPage;
    public static WebDriver driver;

        /**
         * осуществление первоначальной настройки
         */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        calcPage = new CalcPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 5 сек.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("site"));
        //Выбираем из dropdown меню нужную локацию для тестов
    }
    @DataProvider(name="PositiveData")
    //двумерный массив с тестовыми данными. 1. Регион; 2. Вводные данные; 2. ОР.
    public Object[][] dataProviderPositiveRostovRegion() {
        return new Object[][] {
            { 61, "1", "12", },
            { 61, "100", "1200"},
            { 61, "101", "1515" },
            { 61, "150", "2250" },
            { 61, "151", "6795" },
            { 61, "200", "9000" },
            { 61, "201", "15075" },
            { 61, "250", "18750" },
            { 61, "251", "37650" },
            { 61, "2000", "300000" },
            { 1, "1", "10", },
            { 1, "100", "1000"},
            { 1, "101", "2020" },
            { 1, "150", "3000" },
            { 1, "151", "3775" },
            { 1, "200", "5000" },
            { 1, "201", "12060" },
            { 1, "250", "15000" },
            { 1, "251", "30120" },
            { 1, "2000", "240000" }
        };
    }

    @DataProvider(name="NegativeData")
    public Object[][] dataProviderNegative() {
        return new Object[][] {
            { 30, "-1", "red-notification", },
            { 30, "", "red-notification", },
            { 30, "0", "red-notification", },
            { 30, "null", "red-notification", },
            { 30, "1 0 0", "red-notification", },
            { 30, "100,5", "red-notification", },
            { 30, "120.80", "red-notification", },
            { 30, "2001", "red-notification", },
            { 30, "43545984968", "red-notification", },
            { 30, "10e2", "red-notification", },
            { 30, "`", "red-notification", },
            { 30, "'", "red-notification", },
            { 30, "#", "red-notification", },
            { 30, "test", "red-notification", },
        };
    }

    /**
     * тестовый метод
     */
     @Test(dataProvider = "PositiveData", description = "PositiveTests")
     public void calcPositiveTest(int region, String input, String expected) {
        calcPage.select_list(region);
        //очищаем форму
        calcPage.clearField();
        //вводим данные
        calcPage.inputValue(input);
        //нажимаем кнопку отпрвки
        calcPage.clickBtn();
        //Получаем результат со старницы
        String value = calcPage.getResultValue();
        //Сравниваем полученные данные с ОР
        Assert.assertEquals(expected, value);
         //очищаем форму
        calcPage.clearField();
     }
    @Test(dataProvider = "NegativeData", description = "PositiveTests")
    public void calcNegativeTest(int region, String input, String expectedClassNameNotification) {
        calcPage.select_list(region);
        //очищаем форму
        calcPage.clearField();
        //вводим данные
        calcPage.inputValue(input);
        //нажимаем кнопку отпрвки
        calcPage.clickBtn();
        //Сравниваем полученные данные с ОР
        Assert.assertEquals(true, calcPage.getClassNameNotification(expectedClassNameNotification));
    }

    /**
     * Закрытие окна браузера
     */
    @AfterClass
    public static void tearDown() { driver.quit(); }
}
