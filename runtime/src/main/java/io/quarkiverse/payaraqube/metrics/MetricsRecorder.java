package fish.payara.cloud.metrics;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

import io.quarkus.runtime.annotations.Recorder;
import io.quarkus.runtime.metrics.MetricsFactory;

@Recorder
public class MetricsRecorder {

    // TODO: Remove base. for Payara 6 compatible.
    private static final String MEMORY_USED_HEAP = "base.memory.usedHeap";
    private static final String MEMORY_USED_NON_HEAP = "base.memory.usedNonHeap";
    private static final String MEMORY_COMMITTED_HEAP = "base.memory.committedHeap";
    private static final String MEMORY_COMMITTED_NON_HEAP = "base.memory.committedNonHeap";
    private static final String MEMORY_MAX_HEAP = "base.memory.maxHeap";
    private static final String MEMORY_MAX_NON_HEAP = "base.memory.maxNonHeap";

    final String TAG_METRIC_MP_SCOPE_NAME = "mp_scope";
    final String BASE_SCOPE = "base";

    final NavigableMap<String, Supplier<Number>> COUNTERS = new TreeMap<>();

    MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public void initCounters() {
        COUNTERS.put(MEMORY_USED_HEAP, () -> memoryMXBean.getHeapMemoryUsage().getUsed());
        COUNTERS.put(MEMORY_USED_NON_HEAP, () -> memoryMXBean.getNonHeapMemoryUsage().getUsed());
        COUNTERS.put(MEMORY_COMMITTED_HEAP, () -> memoryMXBean.getHeapMemoryUsage().getCommitted());
        COUNTERS.put(MEMORY_COMMITTED_NON_HEAP, () -> memoryMXBean.getNonHeapMemoryUsage().getCommitted());
        COUNTERS.put(MEMORY_MAX_HEAP, () -> memoryMXBean.getHeapMemoryUsage().getMax());
        COUNTERS.put(MEMORY_MAX_NON_HEAP, () -> memoryMXBean.getNonHeapMemoryUsage().getMax());
    }

    public Consumer<MetricsFactory> registerMetrics() {
        return new Consumer<MetricsFactory>() {
            @Override
            public void accept(MetricsFactory metricsFactory) {
                usedHeap(metricsFactory);
                usedNonHeap(metricsFactory);
                committedHeap(metricsFactory);
                committedNonHeap(metricsFactory);
                maxHeap(metricsFactory);
                maxNonHeap(metricsFactory);
            }
        };

    }

    private void usedNonHeap(MetricsFactory metricsFactory) {
        metricsFactory
                .builder(MetricsRecorder.MEMORY_USED_NON_HEAP, MetricsFactory.Type.BASE)
                .description("Displays the amount of used memory in bytes.")
                //                        .tag(TAG_METRIC_MP_SCOPE_NAME, BASE_SCOPE)
                .unit("bytes")
                .buildGauge(COUNTERS.get(MEMORY_USED_NON_HEAP));
    }

    private void usedHeap(MetricsFactory metricsFactory) {
        metricsFactory
                .builder(MetricsRecorder.MEMORY_USED_HEAP, MetricsFactory.Type.BASE)
                .description("Displays the amount of used memory in bytes.")
                //                        .tag(TAG_METRIC_MP_SCOPE_NAME, BASE_SCOPE)
                .unit("bytes")
                .buildGauge(COUNTERS.get(MEMORY_USED_HEAP));
    }

    private void committedHeap(MetricsFactory metricsFactory) {
        metricsFactory
                .builder(MetricsRecorder.MEMORY_COMMITTED_HEAP, MetricsFactory.Type.BASE)
                .description("Displays the amount of memory in bytes that is committed for the JVM to use.")
                //                        .tag(TAG_METRIC_MP_SCOPE_NAME, BASE_SCOPE)
                .unit("bytes")
                .buildGauge(COUNTERS.get(MEMORY_COMMITTED_HEAP));
    }

    private void committedNonHeap(MetricsFactory metricsFactory) {
        metricsFactory
                .builder(MetricsRecorder.MEMORY_COMMITTED_NON_HEAP, MetricsFactory.Type.BASE)
                .description("Displays the amount of memory in bytes that is committed for the JVM to use.")
                //                        .tag(TAG_METRIC_MP_SCOPE_NAME, BASE_SCOPE)
                .unit("bytes")
                .buildGauge(COUNTERS.get(MEMORY_COMMITTED_NON_HEAP));
    }

    private void maxHeap(MetricsFactory metricsFactory) {
        metricsFactory
                .builder(MetricsRecorder.MEMORY_MAX_HEAP, MetricsFactory.Type.BASE)
                .description("Displays the maximum amount of memory in bytes that can be used for HeapMemory.")
                //                        .tag(TAG_METRIC_MP_SCOPE_NAME, BASE_SCOPE)
                .unit("bytes")
                .buildGauge(COUNTERS.get(MEMORY_MAX_HEAP));
    }

    private void maxNonHeap(MetricsFactory metricsFactory) {
        metricsFactory
                .builder(MetricsRecorder.MEMORY_MAX_NON_HEAP, MetricsFactory.Type.BASE)
                .description("Displays the maximum amount of memory in bytes that can be used for NonHeapMemory.")
                //                        .tag(TAG_METRIC_MP_SCOPE_NAME, BASE_SCOPE)
                .unit("bytes")
                .buildGauge(COUNTERS.get(MEMORY_MAX_NON_HEAP));
    }
}
