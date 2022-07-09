package de.griefed;

import java.io.File;

public class Main {
  public static void main(String[] args) {
    File file = new File("Fasel.bar");

    System.out.println(file.toString().substring(file.toString().lastIndexOf(".") + 1));
  }
}
