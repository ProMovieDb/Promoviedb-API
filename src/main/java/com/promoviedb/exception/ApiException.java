package com.promoviedb.exception;

/**
 * Base exception for API errors.
 * <p>
 * This exception is thrown when an API request fails. It contains the HTTP status code
 * and the error response body for debugging purposes.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * try {
 *     MovieDetails movie = client.movieService().getDetails("invalid-id");
 * } catch (ApiException e) {
 *     System.err.println("API error: " + e.getMessage());
 *     System.err.println("Status code: " + e.getStatusCode());
 *     System.err.println("Error body: " + e.getErrorBody());
 * }
 * }</pre>
 *
 * @see AuthenticationException
 * @see RateLimitException
 */
public class ApiException extends RuntimeException {
    private final int statusCode;
    private final String errorBody;

    /**
     * Constructs a new API exception with the specified message.
     *
     * @param message the detail message
     */
    public ApiException(String message) {
        super(message);
        this.statusCode = -1;
        this.errorBody = null;
    }

    /**
     * Constructs a new API exception with the specified message and cause.
     *
     * @param message the detail message
     * @param cause the cause of this exception
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = -1;
        this.errorBody = null;
    }

    /**
     * Constructs a new API exception with the specified status code, message, and error body.
     *
     * @param statusCode the HTTP status code
     * @param message the detail message
     * @param errorBody the response body containing error details
     */
    public ApiException(int statusCode, String message, String errorBody) {
        super(message);
        this.statusCode = statusCode;
        this.errorBody = errorBody;
    }

    /**
     * Returns the HTTP status code associated with this exception.
     *
     * @return the HTTP status code, or -1 if not available
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Returns the error response body.
     *
     * @return the error response body, or null if not available
     */
    public String getErrorBody() {
        return errorBody;
    }
}
