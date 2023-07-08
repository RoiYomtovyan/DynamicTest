package org.example;

public class ResponseData {

    private final String url;
    private final int statusCode;

    public ResponseData(String url, int statusCode) {
        this.url = url;
        this.statusCode = statusCode;
    }

    public String getUrl() {
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
