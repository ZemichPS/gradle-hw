package by.zemich.exception;

public class GitNotInstalledException extends VersioningAbstractException{
    public GitNotInstalledException(String message) {
        super(message);
    }
}
