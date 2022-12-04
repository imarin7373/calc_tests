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
            { "Ростовская область", "1", "12", },
            { "Ростовская область", "100", "1200"},
            { "Ростовская область", "101", "1515" },
            { "Ростовская область", "150", "2250" },
            { "Ростовская область", "151", "6795" },
            { "Ростовская область", "200", "9000" },
            { "Ростовская область", "201", "15075" },
            { "Ростовская область", "250", "18750" },
            { "Ростовская область", "251", "37650" },
            { "Ростовская область", "2000", "300000" },
            { "Алтайский край", "1", "10", },
            { "Алтайский край", "100", "1000"},
            { "Алтайский край", "101", "2020" },
            { "Алтайский край", "150", "3000" },
            { "Алтайский край", "151", "3775" },
            { "Алтайский край", "200", "5000" },
            { "Алтайский край", "201", "12060" },
            { "Алтайский край", "250", "15000" },
            { "Алтайский край", "251", "30120" },
            { "Алтайский край", "2000", "240000" }
        };
    }

    @DataProvider(name="NegativeData")
    public Object[][] dataProviderNegative() {
        return new Object[][] {
            { "Москва", "-1", "red-notification", },
            { "Москва", "", "red-notification", },
                { "Москва", "0", "red-notification", },
                { "Москва", "null", "red-notification", },
                { "Москва", "1 0 0", "red-notification", },
                { "Москва", "100,5", "red-notification", },
                { "Москва", "120.80", "red-notification", },
                { "Москва", "2001", "red-notification", },
                { "Москва", "43545984968", "red-notification", },
                { "Москва", "10e2", "red-notification", },
                { "Москва", "`", "red-notification", },
                { "Москва", "'", "red-notification", },
                { "Москва", "#", "red-notification", },
                { "Москва", "test", "red-notification", },
        };
    }

    /**
     * тестовый метод
     */
     @Test(dataProvider = "PositiveData", description = "PositiveTests")
     public void calcPositiveTest(String region, String input, String expected) {
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
    public void calcNegativeTest(String region, String input, String expectedClassNameNotification) {
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
