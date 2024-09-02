package by.zemich.services;

import by.zemich.services.api.TagProcessorHandler;
import by.zemich.services.tagprocessors.DefaultTagProcessorImpl;
import by.zemich.services.api.TagProcessor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TagProcessorHandlerImpl implements TagProcessorHandler {
    private final Map<String, TagProcessor> tagProcessors = new HashMap<>();

    @Override
    public String process(String branch, String latestTag) {
        return tagProcessors.getOrDefault(branch, new DefaultTagProcessorImpl()).compute(latestTag);
    }

    @Override
    public void addTagProcessors(TagProcessor tagProcessor) {
        tagProcessors.put(tagProcessor.getHandleBranch(), tagProcessor);
    }

    @Override
    public void addTagProcessors(Collection<TagProcessor> tagProcessorCollection) {
        tagProcessorCollection.forEach(this::addTagProcessors);
    }
}
