package by.zemich.tasks;

import by.zemich.exception.ExecuteCommandException;
import by.zemich.extentions.TaggingPluginExtension;
import by.zemich.services.api.GitCommandService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.util.Objects;

public class PushTask extends DefaultTask {

    private GitCommandService commandService;

    public PushTask() {
        setGroup("Versioning");
        setDescription("Adding  a new tag and pushing project to origin");
    }

    @TaskAction
    public void addTagAndPush() {
        try {
            addTag();
            pushToOrigin();
            System.out.println("Pushed.");
        } catch (IOException e) {
            throw new ExecuteCommandException("failed to push to origin", e);
        }
    }

    private void pushToOrigin() throws IOException {
        String currentBranch = commandService.findCurrentBranch();
        commandService.pushToOrigin(currentBranch);
    }

    private void addTag() throws IOException {
        String mewTag = Objects.requireNonNull(getProject().getExtensions().findByType(TaggingPluginExtension.class)).getNewTag();
        commandService.addNewTag(mewTag);
    }

    public void setCommandService(GitCommandService commandService) {
        this.commandService = commandService;
    }
}
