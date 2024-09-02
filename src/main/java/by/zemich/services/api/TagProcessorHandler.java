package by.zemich.services.api;

import java.util.Collection;

public interface TagProcessorHandler {
    String process(String branch, String latestTag);
    public void addTagProcessors(TagProcessor tagProcessor);
    public void addTagProcessors(Collection<TagProcessor>  tagProcessorCollection);
}
