import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {
    LocalDate date = LocalDate.now().plusDays(3);
    
    @Test
    void shouldFormSubmission() {
        open("http://localhost:9999");
        SelenideElement form = $ ("form.form");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$("[data-test-id=date] input").setValue(String.valueOf(date));
        form.$("[data-test-id=name] input").setValue("Иван Иванов");
        form.$("[data-test-id=phone] input").setValue("+79108747630");
        form.$("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }
}
