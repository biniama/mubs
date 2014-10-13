package com.kaufda.exceptions

/**
 * Created by biniam on 10/11/2014.
 */
/**
    A custom exception class is defined which extends RuntimeException
*/
class UserServiceException extends ServiceException {

    public static final int ERROR_PASSWORD_DOES_NOT_MATCH = 1001
    public static final int ERROR_INCORRECT_OLD_USER_PASSWORD = 1002
    public static final int ERROR_CHANGING_PASSWORD = 1003

    def UserServiceException(String message, int code) {
        super(message, code)
    }
}
