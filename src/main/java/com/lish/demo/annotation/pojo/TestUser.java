package com.lish.demo.annotation.pojo;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 测试自动注入
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
@Component
public class TestUser {

    private String httpVersion;

    private String requestMethod;

    private String path;

    private List<Map<String,String>> headers;

    private List<Map<String,Object>> parameters;



    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Map<String, String>> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Map<String, String>> headers) {
        this.headers = headers;
    }

    public List<Map<String, Object>> getParameters() {
        return parameters;
    }

    public void setParameters(List<Map<String, Object>> parameters) {
        this.parameters = parameters;
    }
}
