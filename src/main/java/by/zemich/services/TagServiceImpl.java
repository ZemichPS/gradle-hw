package by.zemich.services;

import by.zemich.services.api.GitCommandService;
import by.zemich.services.api.TagProcessorHandler;
import by.zemich.services.api.TagService;
import by.zemich.services.tagprocessors.*;

import java.util.List;

public class TagServiceImpl implements TagService {
    private final GitCommandService gitCommandService;
    private final TagProcessorHandler processorHandler;

    public TagServiceImpl(GitCommandService gitCommandService) {
        this.gitCommandService = gitCommandService;

        this.processorHandler = new TagProcessorHandlerImpl();
        processorHandler.addTagProcessors(List.of(
                new DevTagProcessorImpl(),
                new MasterTagProcessorImpl(),
                new QaTagProcessorImpl(),
                new StageTagProcessorImpl()
        ));
    }

    @Override
    public String determineNewTag(String branch, String tag) {
        return processorHandler.process(branch, tag);
    }
}
