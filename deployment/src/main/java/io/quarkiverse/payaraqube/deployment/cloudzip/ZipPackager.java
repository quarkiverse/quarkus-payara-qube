package io.quarkiverse.payaraqube.deployment.cloudzip;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipPackager {

    public void zipQuarkusAppDirectory(Path sourceDir, Path zipFilePath) throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath.toFile()))) {
            Files.walk(sourceDir)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(
                                StreamSupport.stream(sourceDir.relativize(path).spliterator(), false)
                                        .map(Path::toString)
                                        .collect(Collectors.joining("/")));
                        try {
                            zipOut.putNextEntry(zipEntry);
                            Files.copy(path, zipOut);
                            zipOut.closeEntry();
                        } catch (IOException e) {
                            throw new RuntimeException("Error processing file for zipping: " + path, e);
                        }
                    });
        }
    }
}
