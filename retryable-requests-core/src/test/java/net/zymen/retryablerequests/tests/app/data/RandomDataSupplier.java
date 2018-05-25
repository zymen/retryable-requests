package net.zymen.retryablerequests.tests.app.data;

import org.apache.commons.text.RandomStringGenerator;

public class RandomDataSupplier {
    private static final RandomStringGenerator stringGenerator =
            new RandomStringGenerator.Builder()
                    .withinRange('a', 'z')
                    .build();

    public static String randomString(final int length) {
        return stringGenerator.generate(length);
    }
}
