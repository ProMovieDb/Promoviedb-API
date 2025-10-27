package com.promoviedb.exception;

/**
 * Exception thrown when API rate limit is exceeded.
 * <p>
 * This exception is thrown when the API returns a 429 (Too Many Requests) status code,
 * indicating that the client has sent too many requests in a given time period.
 * </p>
 *
 * @see ApiException
 */
public class RateLimitException extends ApiException {
    /**
     * Constructs a new rate limit exception with the specified message.
     *
     * @param message the detail message
     */
    public RateLimitException(String message) {
        super(message);
    }

    /**
     * Constructs a new rate limit exception with the specified status code, message, and error body.
     *
     * @param statusCode the HTTP status code (typically 429)
     * @param message the detail message
     * @param errorBody the response body containing error details
     */
    public RateLimitException(int statusCode, String message, String errorBody) {
        super(statusCode, message, errorBody);
    }
}
