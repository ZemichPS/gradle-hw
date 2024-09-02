package by.zemich.tasks;

import by.zemich.exception.ExecuteCommandException;
import by.zemich.extentions.TaggingPluginExtension;
import by.zemich.services.api.GitCommandService;
import by.zemich.services.api.TagProcessorHandler;
import by.zemich.services.api.TagService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;

public class ComputeNewTagTask extends DefaultTask {

    private TagService tagService;
    private GitCommandService gitCommandService;

    public ComputeNewTagTask() {
        setGroup("Versioning");
        setDescription("Calculating a new tag value");
    }

    @TaskAction
    public void computeNewTag() {
        String latestTag = null;
        String branchName = null;
        try {
            latestTag = gitCommandService.findLastTag();
            branchName = gitCommandService.findCurrentBranch();
        } catch (IOException e) {
            throw new ExecuteCommandException("Failed in commands execution", e);
        }
        String newTag = tagService.determineNewTag(branchName, latestTag);
        getProject().getExtensions().getByType(TaggingPluginExtension.class).setNewTag(newTag);
        System.out.println("New tag: " + newTag);
    }

    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    public void setGitCommandService(GitCommandService gitCommandService) {
        this.gitCommandService = gitCommandService;
    }
}
