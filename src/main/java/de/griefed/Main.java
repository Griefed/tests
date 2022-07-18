package de.griefed;

import java.io.File;
import java.io.IOException;
import java.util.List;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.io.FileUtils;

public class Main {

  public static void main(String[] args) throws ZipException {

    ZipFile valid = new ZipFile("Survive_Create_Prosper_4_valid.zip");
    ZipFile invalid = new ZipFile("Survive_Create_Prosper_4_invalid.zip");
    ZipFile jar = new ZipFile("ServerPackCreator-dev.jar");

    List<FileHeader> headersValid = valid.getFileHeaders();
    List<FileHeader> headersInvalid = invalid.getFileHeaders();
    List<FileHeader> headersJar = jar.getFileHeaders();

    String extractPreFix = "temp/";

    System.out.println("Valid");
    headersValid.forEach(
        fileHeader -> {
          if (!fileHeader.isDirectory()) {
            System.out.println("  " + fileHeader.getFileName());
          }
        });

    System.out.println();

    System.out.println("Invalid");
    headersInvalid.forEach(
        fileHeader -> {
          if (!fileHeader.isDirectory()) {
            System.out.println("  " + fileHeader.getFileName());
          }
        });

    System.out.println();

    System.out.println("ServerPackCreator");
    headersJar.forEach(
        fileHeader -> {
          System.out.println("  " + fileHeader.getFileName());
          if (fileHeader.getFileName().equals("BOOT-INF/lib/artemis-journal-2.19.1.jar")) {

            File file = new File(extractPreFix + fileHeader.getFileName());

            if (!file.exists()) {
              try {

                FileUtils.copyInputStreamToFile(jar.getInputStream(fileHeader), file);

              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            }

            try (ZipFile zipFile = new ZipFile(file)) {

              System.out.println("  " + file.getName());
              zipFile.getFileHeaders().forEach(fileHeader1 -> System.out.println("      " + fileHeader1));

            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          }
        });
  }

  private static void getFileFromJarInJar(ZipFile initialJar, String fileOrDirToGet, String... embeddedJars) {

  }
}
