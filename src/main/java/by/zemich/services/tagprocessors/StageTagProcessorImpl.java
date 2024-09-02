package by.zemich.services.tagprocessors;

import by.zemich.services.api.TagProcessor;
import by.zemich.services.util.TagVersionUtil;

public class StageTagProcessorImpl implements TagProcessor {
    @Override
    public String compute(String latestTag) {
        latestTag = TagVersionUtil.prepare(latestTag);
        int minor = TagVersionUtil.getMinor(latestTag);
        int major = TagVersionUtil.getMajor(latestTag);
        return "v" + major + "." + minor + "-rc";
    }

    @Override
    public String getHandleBranch() {
        return "stage";
    }
}
