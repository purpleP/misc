/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package unzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class App {
    
    public static void unzip(File archive, Path destDir) throws Exception {
        Path absDestDir = destDir.toAbsolutePath();
        if (!Files.exists(absDestDir)) {
            Files.createDirectories(absDestDir);
        }
        if (!Files.isDirectory(absDestDir)) {
            throw new FileNotFoundException(String.format(
                "Destination directory: %s for unarchiving does not exists",
                absDestDir
            ));
        }
        try (var in = new ZipInputStream(new FileInputStream(archive))) {
            ZipEntry entry = null;
            while ((entry = in.getNextEntry()) != null) {
                var entryDestPath = absDestDir.resolve(entry.getName());
                if (!entryDestPath.startsWith(absDestDir)) {
                    throw new Exception(String.format(
                        "Illegal zip entry %s", entry
                    ));
                }
                if (entry.isDirectory()) {
                    Files.createDirectory(entryDestPath);
                } else {
                    try (var out = Files.newOutputStream(entryDestPath)) {
                        in.transferTo(out);
                    }
                }
                in.closeEntry();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        for (String arg: args) {
            unzip(Paths.get(arg).toFile(), Paths.get("/tmp"));
        }
    }
}
