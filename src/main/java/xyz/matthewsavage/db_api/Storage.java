package xyz.matthewsavage.db_api;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class Storage {

    private final int APP_ID;

    public Storage(int APP_ID) {
        this.APP_ID = APP_ID;
    }

    public void save(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Path path = Paths.get("/var/www/html/jars/"+ APP_ID + "/app.jar");
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
//            new File("/var/www/html/jars/"+ APP_ID + "/app.jar").setReadable(true);
            Set<PosixFilePermission> globalRead = PosixFilePermissions.fromString("rw-r--r--");
            Files.setPosixFilePermissions(path, globalRead);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        if (!directory.delete()) {
            System.err.println("Failed to delete: " + directory.getAbsolutePath());
        }
    }
}


//    Set<PosixFilePermission> ownerWritable = PosixFilePermissions.fromString("rw-r--r--");
//    FileAttribute<?> permissions = PosixFilePermissions.asFileAttribute(ownerWritable);
//Files.createFile(path, permissions);