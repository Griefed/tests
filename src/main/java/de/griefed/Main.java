package de.griefed;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.io.FileUtils;

public class Main {

  private static String matcher = "[\\w+\\/]+(blockstates|models\\/blocks|textures\\/block)\\/.*";

  public static void main(String[] args) {

    Collection<File> files = FileUtils.listFiles(new File("C:\\Minecraft\\Game\\Instances\\Survive Create Prosper 4\\mods"), new String[] {"jar"}, true);

    for (File file : getBlueMapResources(files)) {
      System.out.println(file.getName());
    }
  }

  private static TreeSet<File> getBlueMapResources(Collection<File> files) {
    TreeSet<File> resources = new TreeSet<>();
    for (File file : files) {
      try (ZipFile zipFile = new ZipFile(file)) {

        if (isBlueMapResourceMod(zipFile)) {
          resources.add(file);
        }

      } catch (IOException e) {
        System.out.println("Error checking jar: " + file.getName());
      }
    }
    return resources;
  }

  private static boolean isBlueMapResourceMod(ZipFile zipFile) throws ZipException {
    return getTexturesAndModelsNStuff(zipFile).size() > 0;
  }

  private static TreeSet<String> getTexturesAndModelsNStuff(ZipFile zipFile) throws ZipException {
    List<FileHeader> fileHeaderList = new ArrayList<>(zipFile.getFileHeaders());
    TreeSet<String> entries = new TreeSet<>();
    for (FileHeader header : fileHeaderList) {
      if (header.getFileName().matches(matcher)) {
        entries.add(header.getFileName());
      }
    }
    return entries;
  }
}
