package by.zemich.services.tagprocessors;

import by.zemich.services.api.TagProcessor;
import by.zemich.services.util.TagVersionUtil;

public class MasterTagProcessorImpl implements TagProcessor {
    @Override
    public String compute(String latestTag) {
        latestTag = TagVersionUtil.prepare(latestTag);
        int major = TagVersionUtil.getMajor(latestTag);
        major++;
        return "v" + major + ".0";
    }

    @Override
    public String getHandleBranch() {
        return "master";
    }
}
