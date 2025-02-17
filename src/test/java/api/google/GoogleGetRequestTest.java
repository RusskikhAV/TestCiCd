package api.google;

import api.BaseApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;

@Tag("Api")
public class GoogleGetRequestTest extends BaseApiTest {
    {
        RestAssured.baseURI = "https://www.google.ru/";
    }

    Map<String, String> qu = new HashMap<>();

    @TmsLink("-")
    @Test
    @Description("test")
    public void checkAddingNewClient() {
        qu.put("q", "Google");
        Response response = standardRequestHandlersService.requestGetMethodWithQueryParams(
                "/search",
                qu
        );

        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "INCORRECT_SC_MSG")
        );
    }
}
