package io.quarkiverse.payaraqube.deployment.config;

import io.quarkiverse.payaraqube.config.PayaraQubeConfigBuilder;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.RunTimeConfigBuilderBuildItem;

public class PayaraCloudConfigSourceProcessor {

    private static final String FEATURE_NAME = "quarkus-config-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE_NAME);
    }

    @BuildStep
    RunTimeConfigBuilderBuildItem configBuilder() {
        return new RunTimeConfigBuilderBuildItem(PayaraQubeConfigBuilder.class.getName());
    }
}