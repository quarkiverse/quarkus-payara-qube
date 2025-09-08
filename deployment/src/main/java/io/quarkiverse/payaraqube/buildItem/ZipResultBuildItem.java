package fish.payara.cloud.buildItem;

import java.nio.file.Path;

import io.quarkus.builder.item.SimpleBuildItem;

public final class ZipResultBuildItem extends SimpleBuildItem {
    private final String zipName;
    private final Path zipPath;

    public ZipResultBuildItem(String zipName, Path zipPath) {
        this.zipName = zipName;
        this.zipPath = zipPath;
    }

    public String getZipName() {
        return zipName;
    }

    public Path getZipPath() {
        return zipPath;
    }

}
