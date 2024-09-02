package by.zemich.tasks;

import by.zemich.exception.ExecuteCommandException;
import by.zemich.exception.UncommitedFilesExist;
import by.zemich.services.api.GitCommandService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;

public class UncommittedCheckTask  extends DefaultTask {

    private GitCommandService gitCommandService;

    public UncommittedCheckTask() {
        setGroup("Versioning");
        setDescription("Checking for uncommitted files");
    }

    @TaskAction
    public void doCheck(){
        try {
            boolean uncommited = gitCommandService.isUncommittedFilesPresents();
            if (uncommited) {
                String latestTag = gitCommandService.findLastTag();
                System.out.println("Uncommitted changes found. Tagging as " + latestTag + ".uncommitted");
                throw new UncommitedFilesExist("Uncommitted files exist");
            }
            System.out.println("All necessary files has been commited");

        } catch (IOException e) {
            throw new ExecuteCommandException("Failed to execute command.", e);
        }
    }

    public void setGitCommandService(GitCommandService gitCommandService) {
        this.gitCommandService = gitCommandService;
    }
}
