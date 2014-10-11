package com.kaufda.exceptions

/**
 * Created by biniam on 10/11/2014.
 */
class UserServiceException extends ServiceException {

    public static final int ERROR_PASSWORD_DOES_NOT_MATCH = 1001
    public static final int ERROR_USER_CANNOT_BE_CREATED = 1002

    def UserServiceException(String message, int code) {
        super(message, code)
    }
}
