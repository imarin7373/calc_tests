<h3>Задача: написать автотесты для калькулятора дорожного налога на <a href="https://law.drom.ru/calc/">drom.ru</a></h3>
<p>Для запуска тестов в Chrome скопировать <a href="https://chromedriver.chromium.org/downloads">chromedriver</a> соответвующий версии браузера и поместить в <code>src/test/resources/webdriver</code></p>
<p>Для эксперимента берем два региона Алтайский край и Саратовскую область.</p>
<p>Исходя из данных по рассчету дор.налога в этих регионах, составяем таблицы с позитивными проверками (граничными значениями) и таблицу с негативными проверками.</p>
<h4>Ростовская область</h4>
<table>
  <thead>
    <tr>
      <td>Автомобили легковые</td>
      <td>Ставка</td>
    </tr>
  </thead>
  <tr>
    <td>до 100 л.с. (до 73,55 кВт) включительно</td>
    <td>12</td>
  </tr>
    <tr>
    <td>свыше 100 л.с. до 150 л.с.  включительно</td>
    <td>15</td>
  </tr>
  <tr>
    <td>свыше 150 л.с. до 200 л.с.  включительно</td>
    <td>45</td>
  </tr>
  <tr>
    <td>свыше 200 л.с. до 250 л.с.  включительно</td>
    <td>75</td>
  </tr>
  <tr>
    <td>свыше 250 л.с.</td>
    <td>150</td>
  </tr>
</table>

<h4>Алтайский край</h4>
<table>
  <thead>
    <tr>
      <td>Автомобили легковые</td>
      <td>Ставка</td>
    </tr>
  </thead>
  <tr>
    <td>до 100 л.с. (до 73,55 кВт) включительно</td>
    <td>10</td>
  </tr>
  <tr>
    <td>свыше 100 л.с. до 150 л.с. включительно</td>
    <td>20</td>
  </tr>
  <tr>
    <td>свыше 150 л.с. до 200 л.с. включительно</td>
    <td>25</td>
  </tr>
  <tr>
    <td>свыше 200 л.с. до 250 л.с. включительно</td>
    <td>60</td>
  </tr>	
  <tr>
    <td>свыше 250 л.с. (свыше 183,9 кВт)</td>
    <td>120</td>
  </tr>		
</table>

<h4>Позитивные проверки</h4>

<p>Составляем таблицу: 1. номер региона в dropdown (1 - Алтайский край, 61 - Саратовская область), 2. вводимые данные, 3. ожидаемый результат</p>

<table>
  <thead>
    <tr>
	    <td>id reg</td>
	    <td>value</td>
	    <td>expected</td>
	  </tr>
	</thead>
    <tr>
      <td>61</td>
      <td>"1"</td>
      <td>"12"</td>
    </tr>
		<tr>
			<td>61</td>
			<td>"100"</td>
			<td>"1200"</td>
		</tr>
	<tr>
		<td>61</td>
		<td>"101"</td>
		<td>"1515"</td>
	</tr>
	<tr>
		<td>61</td>
		<td>"150"</td>
		<td>"2250"</td>
	</tr>
	<tr>
		<td>61</td>
		<td>"151"</td>
		<td>"6795"</td>
	</tr>
	<tr>
		<td>61</td>
		<td>"200"</td>
		<td>"9000"</td>
	</tr>
	<tr>
		<td>61</td>
		<td>"201"</td>
		<td>"15075"</td>
	</tr>
	<tr>
		<td>61</td>
		<td>"250"</td>
		<td>"18750"</td>
	</tr>
	<tr>
		<td>61</td>
		<td>"251"</td>
		<td>"37650"</td>
	</tr>
	<tr>
		<td>61</td>
		<td>"2000"</td>
		<td>"300000"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"1"</td>
		<td>"10"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"100"</td>
		<td>"1000"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"101"</td>
		<td>"2020"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"150"</td>
		<td>"3000"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"151"</td>
		<td>"3775"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"200"</td>
		<td>"5000"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"201"</td>
		<td>"12060"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"250"</td>
		<td>"15000"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"251"</td>
		<td>"30120"</td>
	</tr>
	<tr>
		<td>1</td>
		<td>"2000"</td>
		<td>"240000"</td>
	</tr>
</table>

<h4>Негативные проверки</h4>

<p>Для негативных провекрок выберем регион Москва (30 - в dropdown). Ожидаемый результат появление в поле ответа класса "red-notification"</p>

<table>
  <thead>
    <tr>
	    <td>id reg</td>
	    <td>value</td>
	    <td>expected</td>
	  </tr>
	</thead>
	<tr>
		<td>30</td>
		<td>"-1"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>""</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"0"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"null"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"1 0 0"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"100,5"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"120.80"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"2001"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"43545984968"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"10e2"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"`"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"'"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"#"</td>
		<td>"red-notification"</td>
	</tr>
	<tr>
		<td>30</td>
		<td>"test"</td>
		<td>"red-notification"</td>
	</tr>
</table>
	
