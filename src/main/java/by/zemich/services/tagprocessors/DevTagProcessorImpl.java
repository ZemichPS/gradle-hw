package by.zemich.services.tagprocessors;

import by.zemich.services.api.TagProcessor;
import by.zemich.services.util.TagVersionUtil;

public class DevTagProcessorImpl implements TagProcessor {
    @Override
    public String compute(String latestTag) {
        latestTag = TagVersionUtil.prepare(latestTag);
        int major = TagVersionUtil.getMajor(latestTag);
        int minor = TagVersionUtil.getMinor(latestTag);
        minor = minor + 1;
        return "v" + major + "." + minor;
    }

    @Override
    public String getHandleBranch() {
        return "dev";
    }
}
