package by.zemich.services.api;

public interface TagProcessor {

    String compute(String latestTag);

    String getHandleBranch();
}
