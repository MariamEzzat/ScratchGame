package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ConfigLoader {
    private final ObjectMapper mapper = new ObjectMapper();

    public Config loadConfig(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), Config.class);
    }
}