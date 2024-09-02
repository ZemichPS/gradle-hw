package by.zemich.exception;

public abstract class VersioningAbstractException extends RuntimeException {
    public VersioningAbstractException(String message) {
        super(message);
    }

    public VersioningAbstractException(String message, Throwable cause) {
        super(message, cause);
    }
}
