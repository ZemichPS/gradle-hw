package by.zemich.services.api;

import java.io.IOException;

public interface GitCommandService {
    boolean isGitInstalled() throws IOException;
    boolean isRemoteAdded() throws IOException;
    boolean isGitRepository() throws IOException;
    boolean isUncommittedFilesPresents() throws IOException;
    String findLastTag() throws IOException;
    String findCurrentBranch() throws IOException;

    void addNewTag(String tag) throws IOException;
    void pushToOrigin(String branchName) throws IOException;
}
