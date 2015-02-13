package util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class Fixtures {
    public static String read(String fixtureName) {
        try {
            return IOUtils.toString(Fixtures.class.getClassLoader().getResourceAsStream("fixtures/" + fixtureName + ".json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
