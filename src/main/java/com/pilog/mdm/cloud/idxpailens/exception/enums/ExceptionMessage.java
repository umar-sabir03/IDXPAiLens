package com.pilog.mdm.cloud.idxpailens.exception.enums;

public enum ExceptionMessage {
    INERNAL_SERVER_ERROR("Internal server error", "internal.server.error"), USER_NOT_DELETED("User is not deleted.", "user.is.not.deleted"), NO_DATA_FOUND("No data found.", "no.data.found");

    private final String message;
    private final String errorCode;

    ExceptionMessage(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}


