package com.talentport.serverless.responses;

import com.talentport.serverless.models.TalentportResponse;
import com.talentport.serverless.utilities.TalentportObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * POJO containing response object for API Gateway.
 */
public class GatewayExceptionResponse {

    private final String REQUEST_TYPE = "application/json;charset=UTF-8";
    private Map body;
    private Map<String, String> headers;
    private final int statusCode = 500;


    public GatewayExceptionResponse(String messageException) {
        headers = new HashMap<>();
        headers.put("Content-Type", REQUEST_TYPE);
        headers.put("X-Custom-Header", REQUEST_TYPE);
        body = TalentportObjectMapper.convertValue(new TalentportResponse(false, null, messageException), Map.class);
    }

    public Map getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
