package api;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import service.StandardRequestHandlersService;

@Data
@Log4j2
public class BaseApiTest {
    protected StandardRequestHandlersService standardRequestHandlersService;

    public BaseApiTest() {
        standardRequestHandlersService = new StandardRequestHandlersService();
    }

    @BeforeEach
    public void beforeTestLogging(TestInfo info) {
        String testLink = info.getTestMethod()
                .get()
                .getAnnotation(TmsLink.class).value();
        String testDescription = info.getTestMethod()
                .get()
                .getAnnotation(Description.class).value();
        log.info("\n_____________________________________________________\n");
        log.info(String.format("Test-case link: %s\nTest description: %s\n", testLink, testDescription));
    }
}
