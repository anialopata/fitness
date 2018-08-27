package com.anialopata.model;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Created by Ania on 2018-08-27.
 */
public class RestResponse implements Serializable {

    private String message;
    private HttpStatus status;
    private int code;

    public RestResponse(HttpStatus status, String message, int code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
