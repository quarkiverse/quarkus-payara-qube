package io.quarkiverse.payaraqube.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayaraQubeConfigSource implements ConfigSource {
    private static final Logger log = LoggerFactory.getLogger(PayaraQubeConfigSource.class);
    private static final String CONFIG_PATH = "/config/application.properties";
    private static final String MICROPROFILE_CONFIG_PATH = "/config/microprofile-config.properties";
    private final Map<String, String> properties = new HashMap<>();

    public PayaraQubeConfigSource() {
        loadProperties();
    }

    @Override
    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

    @Override
    public String getValue(String key) {
        return properties.get(key);
    }

    @Override
    public String getName() {
        return PayaraQubeConfigSource.class.getSimpleName();
    }

    @Override
    public int getOrdinal() {
        return 500;
    }

    private void loadProperties() {
        loadFromPath(Path.of(CONFIG_PATH));
        loadFromPath(Path.of(MICROPROFILE_CONFIG_PATH));
        // Load additional secret properties if needed
        try {
            Path secretsPath = Path.of("/config/secret-config");
            if (Files.exists(secretsPath)) {
                try (var paths = Files.walk(secretsPath)) {
                    paths.filter(Files::isRegularFile)
                            .filter(path -> path.toString().endsWith(".properties"))
                            .forEach(this::loadFromPath);
                }
            } else {
                log.info("Secrets config directory not found at: {}", secretsPath);
            }
        } catch (IOException e) {
            log.error("Error while loading secrets config files", e);
        }
    }

    private void loadFromPath(Path path) {
        try {
            if (Files.exists(path)) {
                log.info("Loading properties from: {}", path);
                Properties properties = new Properties();
                properties.load(Files.newInputStream(path));
                this.properties.putAll(properties.entrySet().stream()
                        .collect(HashMap::new, (m, v) -> m.put(v.getKey().toString(), v.getValue().toString()),
                                HashMap::putAll));
            } else {
                log.info("No properties found at: {}", path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + path, e);
        }
    }
}
