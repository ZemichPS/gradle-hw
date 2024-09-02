package by.zemich.plugin;

import by.zemich.extentions.TaggingPluginExtension;
import by.zemich.services.GitCommandServiceImpl;
import by.zemich.services.TagServiceImpl;
import by.zemich.services.api.GitCommandService;
import by.zemich.services.api.TagService;
import by.zemich.tasks.CheckRequirementsTask;
import by.zemich.tasks.ComputeNewTagTask;
import by.zemich.tasks.PushTask;
import by.zemich.tasks.UncommittedCheckTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;


public class VersioningPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        GitCommandService commandService = new GitCommandServiceImpl(project);
        TagService tagService = new TagServiceImpl(commandService);

        TaggingPluginExtension extension = project.getExtensions().create("taggingConfig", TaggingPluginExtension.class);

        project.getTasks().create("checkRequirements", CheckRequirementsTask.class, task -> {
            task.setCommandService(commandService);
        });

        project.getTasks().create("uncommittedCheck", UncommittedCheckTask.class, task -> {
            task.setGitCommandService(commandService);
        });

        project.getTasks().create("computeNewTag", ComputeNewTagTask.class, task -> {
            task.setTagService(tagService);
            task.setGitCommandService(commandService);
        });

        project.getTasks().create("push", PushTask.class, task -> {
            task.setCommandService(commandService);
        });

        project.getTasks().getByName("push").dependsOn("computeNewTag");
        project.getTasks().getByName("computeNewTag").dependsOn("uncommittedCheck");
        project.getTasks().getByName("uncommittedCheck").dependsOn("checkRequirements");

    }


}
