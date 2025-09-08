package fish.payara.cloud.deployment.config;

import fish.payara.cloud.config.PayaraCloudConfigBuilder;
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
        System.out.println("Registering Quarkus Config Source");
        return new RunTimeConfigBuilderBuildItem(PayaraCloudConfigBuilder.class.getName());
    }
}