package by.zemich.services.tagprocessors;

import by.zemich.services.api.TagProcessor;
import by.zemich.services.util.TagVersionUtil;

public class DefaultTagProcessorImpl implements TagProcessor {
    @Override
    public String compute(String latestTag) {
        latestTag = TagVersionUtil.prepare(latestTag);
        return latestTag + "-SNAPSHOT";
    }

    @Override
    public String getHandleBranch() {
        return "default";
    }
}
