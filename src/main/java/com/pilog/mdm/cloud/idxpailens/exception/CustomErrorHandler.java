package com.pilog.mdm.cloud.idxpailens.exception;

import com.pilog.mdm.cloud.idxpailens.exception.enums.ExceptionMessage;
import com.pilog.mdm.cloud.idxpailens.payloads.ErrorResponseDto;
import com.pilog.mdm.cloud.idxpailens.utils.PilogUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomErrorHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(ExceptionMessage.INERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionMessage.INERNAL_SERVER_ERROR.getErrorCode()));
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponseDto> handleInternalServerException(InternalServerException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(ex.getMessage(), ex.getStatusCode(), ex.getErrorCode()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(notFoundException.getStatusCode()).body(getErrorResponse(notFoundException.getMessage(), notFoundException.getRawStatusCode(), notFoundException.getStatusText()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException badRequestException) {
        return ResponseEntity.status(badRequestException.getStatusCode()).body(getErrorResponse(badRequestException.getMessage(), badRequestException.getRawStatusCode(), badRequestException.getStatusText()));
    }


    public static ErrorResponseDto getErrorResponse(String message, Integer statusCode, String errorCode) {
        return ErrorResponseDto.builder().message(message).timestamp(PilogUtils.getCurrentDateTime()).status(statusCode).errorCode(errorCode).build();
    }
}
