package io.quarkiverse.payaraqube.deployment.metrics;

import java.util.Optional;

import io.quarkiverse.payaraqube.metrics.MetricsRecorder;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.metrics.MetricsCapabilityBuildItem;
import io.quarkus.deployment.metrics.MetricsFactoryConsumerBuildItem;

public class MemoryMetricProcessor {

    private static final String FEATURE = "memory-metrics";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void registerMetrics(MetricsRecorder recorder,
            BuildProducer<MetricsFactoryConsumerBuildItem> metrics,
            Optional<MetricsCapabilityBuildItem> metricsCapability) {
        if (metricsCapability.isPresent()) {
            recorder.initCounters();
            metrics.produce(new MetricsFactoryConsumerBuildItem(recorder.registerMetrics()));
        }
    }
}
