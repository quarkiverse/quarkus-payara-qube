package io.quarkiverse.payaraqube.config;

import io.quarkus.runtime.configuration.ConfigBuilder;
import io.smallrye.config.SmallRyeConfigBuilder;

public class PayaraQubeConfigBuilder implements ConfigBuilder {
    @Override
    public SmallRyeConfigBuilder configBuilder(SmallRyeConfigBuilder builder) {
        return builder.withSources(new PayaraQubeConfigSource());
    }
}
