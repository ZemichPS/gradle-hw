package by.zemich.services.tagprocessors;

import by.zemich.services.api.TagProcessor;
import by.zemich.services.util.TagVersionUtil;

public class QaTagProcessorImpl implements TagProcessor {
    @Override
    public String compute(String latestTag) {
        latestTag = TagVersionUtil.prepare(latestTag);
        int major = TagVersionUtil.getMajor(latestTag);
        int minor = TagVersionUtil.getMinor(latestTag);
        minor++;
        return "v" + major + "." + minor;
    }

    @Override
    public String getHandleBranch() {
        return "qa";
    }
}
