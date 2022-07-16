package de.griefed;

import java.util.List;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class Main {

  public static void main(String[] args) throws ZipException {

    List<FileHeader> headersValid =
        new ZipFile("Survive_Create_Prosper_4_valid.zip").getFileHeaders();
    List<FileHeader> headersInvalid =
        new ZipFile("Survive_Create_Prosper_4_invalid.zip").getFileHeaders();

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
  }
}
