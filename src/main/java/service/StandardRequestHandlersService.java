package service;

import api.core.RequestParam;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.PARAMETER;
import static io.restassured.http.Method.*;

public class StandardRequestHandlersService {

    public Response requestPostMethodWithUrlAndPojoBody(String url, Object requestBody) {
        return sendSimpleRequest(POST, url, requestBody);
    }

    public Response requestGetMethodWithQueryParams(String url, Map<String, String> queryParams) {
        List<RequestParam> params = queryParams
                .entrySet()
                .stream()
                .map(entry -> getRP(PARAMETER, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return sendSimpleRequest(GET, url, params);
    }

    public Response requestPutMethodWithUrlAndPojoBody(String url, Object requestBody, String uuid) {
        return sendSimpleRequest(PUT, url, uuid, requestBody);
    }
}
