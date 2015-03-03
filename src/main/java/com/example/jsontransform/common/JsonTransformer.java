package com.example.jsontransform.common;

import com.bazaarvoice.jolt.JsonUtils;
import com.bazaarvoice.jolt.Shiftr;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
public class JsonTransformer {
    public String transform(String specFile, String url) {
        URL resource = JsonTransformer.class.getClassLoader().getResource("jsontransforms/" + specFile + ".json");
        Object spec = JsonUtils.filepathToObject(resource.getPath());
        Shiftr shiftr = new Shiftr(spec);
        try {
            InputStream inputStream = new URL(url).openStream();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(shiftr.transform(JsonUtils.jsonToObject(inputStream)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
