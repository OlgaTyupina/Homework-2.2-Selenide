import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {
    @Test
    void shouldFormSubmission() {
        open("http://localhost:9999");
        SelenideElement form = $ ("form.form");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        final String FORMAT_DATE = "dd.MM.yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.plusDays(3);
        String futureDate = dateFormatter.format(newDate);
        form.$("[data-test-id=date] input").setValue(futureDate);
        form.$("[data-test-id=name] input").setValue("Иван Иванов");
        form.$("[data-test-id=phone] input").setValue("+79108747630");
        form.$("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }
}
