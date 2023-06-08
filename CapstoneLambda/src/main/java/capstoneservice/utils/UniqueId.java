package capstoneservice.utils;

import org.gradle.internal.impldep.org.apache.commons.lang.RandomStringUtils;


public class UniqueId {
    public static String generate() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
}

