package de.griefed;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;

public class Main {

  private static final String BASE_URL = "https://meta.legacyfabric.net/";
  private static final String GAME_VERSIONS = BASE_URL + "v2/versions/game";
  private static final String LOADER_VERSIONS = BASE_URL + "v2/versions/loader";
  private static final String INSTALLER_VERSIONS = "https://maven.legacyfabric.net/net/legacyfabric/fabric-installer/maven-metadata.xml";
  private static final ObjectMapper MAPPER = new ObjectMapper()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

  public static void main(String[] args) throws IOException {
    GameVersions gameVersions = new GameVersions(new URL(GAME_VERSIONS), MAPPER);
    LoaderVersions loaderVersions = new LoaderVersions(new URL(LOADER_VERSIONS), MAPPER);
    InstallerVersions installerVersions = new InstallerVersions();

    System.out.println("Game Versions:");
    System.out.println("Stable:");
    gameVersions.releases().forEach(System.out::println);
    System.out.println();
    System.out.println("Snapshots:");
    gameVersions.snapshots().forEach(System.out::println);
    System.out.println();
    System.out.println("All:");
    gameVersions.all().forEach(System.out::println);

    System.out.println();
    System.out.println("---------------------------------------------------------------------------");
    System.out.println();

    System.out.println("Loader Versions:");
    System.out.println("Stable:");
    loaderVersions.releases().forEach(System.out::println);
    System.out.println();
    System.out.println("Snapshots:");
    loaderVersions.snapshots().forEach(System.out::println);
    System.out.println();
    System.out.println("All:");
    loaderVersions.all().forEach(System.out::println);

    System.out.println();
    System.out.println("---------------------------------------------------------------------------");
    System.out.println();

    System.out.println("Release:");
    System.out.println(installerVersions.release());
    System.out.println();
    System.out.println("Latest:");
    System.out.println(installerVersions.latest());
    System.out.println();
    System.out.println("All:");
    installerVersions.all().forEach(System.out::println);

    installerVersions.downloadFile("installer.jar",installerVersions.releaseURL());

    installerVersions.install("1.7.10","0.14.9");
  }
}
