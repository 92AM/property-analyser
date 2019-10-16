package uk.co.rightmove.propertyanalyser.exception;

public class PropertyAnalyserException extends RuntimeException {

    private static final long serialVersionUID = 2113630083710324635L;

    /**
     * PropertyAnalyserException constructor with custom message.
     *
     * @param message String
     */
    public PropertyAnalyserException(String message) {
        super(message);
    }

    /**
     * PropertyAnalyserException constructor with custom message and cause of exception.
     *
     * @param message String
     * @param cause Throwable
     */
    public PropertyAnalyserException(String message, Throwable cause) {
        super(message, cause);
    }

}
