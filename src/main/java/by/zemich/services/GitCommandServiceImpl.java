package by.zemich.services;

import by.zemich.services.api.GitCommandService;
import groovy.lang.Closure;
import org.gradle.api.Project;
import org.gradle.process.ExecSpec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GitCommandServiceImpl implements GitCommandService {

    private final Project project;

    public GitCommandServiceImpl(Project project) {
        this.project = project;
    }

    @Override
    public boolean isGitInstalled() throws IOException {
        String command = "git --version";
        return executeCommand(command).contains("version");
    }

    @Override
    public boolean isRemoteAdded() throws IOException {
        String command = "git remote -v";
        return !executeCommand(command).isBlank();
    }

    @Override
    public boolean isGitRepository() throws IOException {
        String command = "git remote -v";
        return !executeCommand(command).contains("fatal: not a git repository");
    }

    @Override
    public boolean isUncommittedFilesPresents() throws IOException {
        String command = "git status";
        String result =executeCommand(command)
                .trim()
                .toLowerCase();

        return result.contains("changes to be committed");
    }

    @Override
    public String findLastTag() throws IOException {
        String command = "git describe --tags --abbrev=0";
        String result = executeCommand(command);
        if (result.contains("fatal")) return  "";
        return result.trim();
    }

    @Override
    public String findCurrentBranch() throws IOException {
        String command = "git rev-parse --abbrev-ref HEAD";
        return executeCommand(command);
    }

    @Override
    public void addNewTag(String tag) throws IOException {
        String command = "git tag " + tag;
        executeCommand(command);
    }

    @Override
    public void pushToOrigin(String branchName) throws IOException {
        String command = "git push origin " + branchName;
        executeCommand(command);
    }

    private String executeCommand(String command) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Closure<Void> execClosure = new Closure<Void>(this, this) {
                public void doCall(ExecSpec execSpec) {
                    execSpec.commandLine(command.split(" "));
                    execSpec.setStandardOutput(outputStream);
                    execSpec.setErrorOutput(outputStream);
                    execSpec.setIgnoreExitValue(true);
                }
            };
            project.exec(execClosure);
            return outputStream.toString().trim();
        }
    }


}
