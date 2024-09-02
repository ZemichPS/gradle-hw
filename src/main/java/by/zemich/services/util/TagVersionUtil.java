package by.zemich.services.util;

public final class TagVersionUtil {

    private TagVersionUtil() {
    }

    public static String prepare(String latestTag) {
        return latestTag
                .replace("v", "")
                .replace("-SNAPSHOT", "")
                .trim();
    }

    public static int getMajor(String latestTag) {
        if (!latestTag.contains(".")) return 0;
        String[] versions = latestTag.split("\\.");
        if(versions.length < 1) return 0;
        char ch = versions[0].toCharArray()[0];
        if (!Character.isDigit(ch)) return 0;
        return Integer.parseInt(latestTag.split("\\.")[0]);
    }

    public static int getMinor(String latestTag) {
        if (!latestTag.contains(".")) return 0;
        String[] versions = latestTag.split("\\.");
        if(versions.length < 2) return 0;
        char ch = versions[1].toCharArray()[0];
        if (!Character.isDigit(ch)) return 0;
        return Integer.parseInt(latestTag.split("\\.")[1]);
    }


}
