package fish.payara.cloud.deployment.cloudzip;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fish.payara.cloud.cloudzip.ZipPackager;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.pkg.builditem.ArtifactResultBuildItem;
import io.quarkus.deployment.pkg.builditem.JarBuildItem;

public class ZipPackagingProcessor {

    private static final Logger log = LoggerFactory.getLogger(ZipPackagingProcessor.class);

    private static final String FEATURE = "zip-packaging";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    public ArtifactResultBuildItem zipResult(JarBuildItem jarBuildItem) {
        log.info("Packaging Quarkus App to Zip file");
        Path quarkusAppDir = jarBuildItem.getPath().getParent();
        Path app = quarkusAppDir.resolve("app");
        AtomicReference<String> appName = new AtomicReference<>("quarkus-app.zip");
        Arrays.stream(app.toFile().listFiles()).forEach(file -> {
            if (file.getName().contains(".jar")) {
                appName.set(file.getName().replace(".jar", ".zip"));
            }
        });

        Path sourceDir = quarkusAppDir;
        log.info("Source Dir: {}", sourceDir);
        Path zipFilePath = quarkusAppDir.getParent().resolve(appName.get());
        log.info("Zip File Path: {}", zipFilePath);
        ZipPackager zipPackager = new ZipPackager();
        try {
            zipPackager.zipQuarkusAppDirectory(sourceDir, zipFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Error while packaging to Zip file", e);
        }
        log.info("Packaging Quarkus App to Zip file completed");
        return new ArtifactResultBuildItem(zipFilePath, appName.get(), Collections.emptyMap());
    }

}
