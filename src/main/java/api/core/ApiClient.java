package api.core;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;
import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT;

public class ApiClient {
    private final static int CONNECTION_TIMEOUT_VALUE = 5000;
    private final static int SO_TIMEOUT_VALUE = 5000;

    static {
        RestAssured.filters(List.of(
                new RequestLoggingFilter(LogDetail.ALL),
                new ResponseLoggingFilter(LogDetail.BODY),
                new ResponseLoggingFilter(LogDetail.STATUS)
        ));
    }

    public static Response sendSimpleRequest(Method method, String address, List<RequestParam> paramsTable) {
        RequestSender request = createRequest(paramsTable);
        return request.request(method, address);
    }

    public static Response sendSimpleRequest(Method method, String address, RequestParam param) {
        return sendSimpleRequest(method, address, List.of(param));
    }

    public static Response sendSimpleRequest(Method method, String URI, String address, Object pojo) {
        RequestSender request = createRequestWithPojo(pojo);
        return request.request(method, URI, address);
    }

    public static Response sendSimpleRequest(Method method, String address, Object pojo) {
        RequestSender request = createRequestWithPojo(pojo);
        return request.request(method, address);
    }

    public static RequestSender createRequest(List<RequestParam> paramsTable) {
        return requestParamsSetter(getRequestSpecification(), paramsTable);
    }

    public static RequestSender createRequestWithPojoAndParams(List<RequestParam> paramsTable, Object pojo) {
        return requestParamsSetter(getRequestSpecification(), paramsTable).body(pojo);
    }

    public static RequestSender createRequestWithPojo(Object pojo) {
        return getRequestSpecification().body(pojo);
    }

    private static RequestSpecification requestParamsSetter(RequestSpecification request, List<RequestParam> paramsTable) {
        for (RequestParam requestParam : paramsTable) {
            String name = requestParam.getName();
            String value = requestParam.getValue();

            switch (requestParam.getType()) {
                case COOKIE -> request.cookie(name, value);
                case PARAMETER -> request.param(name, value);
                case HEADER -> request.header(name, value);
                case BODY -> request.body(value);
                case PATH -> request.pathParam(name, value);
                case QUERY_PARAMETER -> request.queryParam(name, value);
                default -> throw new IllegalArgumentException(
                        String.format("Некорректно задан тип %s для параметра запроса %s ",
                                requestParam.getType(), name));
            }
        }
        return request;
    }

    private static RequestSpecification getRequestSpecification() {
        return given()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .config(RestAssured.config().httpClient(httpClientConfig()
                        .setParam(CONNECTION_TIMEOUT, CONNECTION_TIMEOUT_VALUE)
                        .setParam(SO_TIMEOUT, SO_TIMEOUT_VALUE)))
                .filter(new AllureRestAssured());
    }
}
