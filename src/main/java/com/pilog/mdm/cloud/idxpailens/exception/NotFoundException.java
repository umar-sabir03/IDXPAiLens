package com.pilog.mdm.cloud.idxpailens.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;

public class NotFoundException extends HttpStatusCodeException {


    private static final long serialVersionUID = 1L;

    public NotFoundException(HttpStatus errorCode, String message, String statusText) {
        super(message, errorCode, statusText, (HttpHeaders)null, (byte[])null, (Charset)null);
    }


}
