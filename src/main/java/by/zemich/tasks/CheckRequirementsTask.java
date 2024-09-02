package by.zemich.tasks;

import by.zemich.exception.ExecuteCommandException;
import by.zemich.exception.GitNotInstalledException;
import by.zemich.services.api.GitCommandService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;

public class CheckRequirementsTask extends DefaultTask {

    private GitCommandService commandService;

    public CheckRequirementsTask() {
        setGroup("Versioning");
        setDescription("Checking for git installation and connecting a remote repository");
    }

    @TaskAction
    public void doCheck() {
        try {
            boolean gitRepository = commandService.isGitRepository();
            boolean installed = commandService.isGitInstalled();
            boolean remoteAdded = commandService.isRemoteAdded();
            if (!gitRepository) throw new GitNotInstalledException("Fatal: not a git repository");
            if (!installed) throw new GitNotInstalledException("Git is not installed");
            if (!remoteAdded) throw new GitNotInstalledException("Remote branch is not added");

            System.out.println("Everything is OK");

        } catch (IOException e) {
            throw new ExecuteCommandException("Failed to execute command.", e);
        }
    }

    public void setCommandService(GitCommandService commandService) {
        this.commandService = commandService;
    }
}

