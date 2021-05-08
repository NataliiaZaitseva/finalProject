package utilits;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneratorEmail {

    public static String generateEmail(String domain, int length, String name) {
        return name + RandomStringUtils.random(length, "ahgtklnb") + "@" + domain;
    }
}
