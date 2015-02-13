package util;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Jolt {
    public static String transform(String spec, String url) {
        List<Object> shiftSpec = JsonUtils.classpathToList("/resources/jsontranforms/" + spec + ".json");
        Chainr chainr = Chainr.fromSpec(shiftSpec);

        InputStream inputStream;
        try {
            inputStream = new URL(url).openStream();
            return (String) chainr.transform(IOUtils.toString(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
