package com.promoviedb.exception;

/**
 * Exception thrown when authentication fails.
 * <p>
 * This exception is typically thrown when the API returns a 401 (Unauthorized)
 * or 403 (Forbidden) status code, indicating that the API key is invalid,
 * missing, or does not have sufficient permissions.
 * </p>
 *
 * @see ApiException
 */
public class AuthenticationException extends ApiException {
    /**
     * Constructs a new authentication exception with the specified message.
     *
     * @param message the detail message
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * Constructs a new authentication exception with the specified status code, message, and error body.
     *
     * @param statusCode the HTTP status code (typically 401 or 403)
     * @param message the detail message
     * @param errorBody the response body containing error details
     */
    public AuthenticationException(int statusCode, String message, String errorBody) {
        super(statusCode, message, errorBody);
    }
}
