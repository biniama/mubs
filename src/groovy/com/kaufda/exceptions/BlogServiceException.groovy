package com.kaufda.exceptions

/**
 * Created by biniam on 10/11/2014.
 */
/**
    A custom exception class is defined which extends RuntimeException
*/
class BlogServiceException extends ServiceException {

    public static final int ERROR_SAVING_BLOG_TO_USER = 2001
    public static final int ERROR_SAVING_BLOG_ENTRY = 2002
    public static final int ERROR_SAVING_BLOG_ENTRY_TO_BLOG = 2003
    public static final int ERROR_SAVING_BLOG = 2004

    def BlogServiceException(String message, int code) {
        super(message, code)
    }
}
