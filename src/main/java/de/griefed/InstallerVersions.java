package de.griefed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;

public class InstallerVersions {

  private final File MANIFEST = new File("manifest.xml");
  private final String INSTALLER_URL = "https://maven.legacyfabric.net/net/legacyfabric/fabric-installer/%s/fabric-installer-%s.jar ";
  private final List<String> ALL = new ArrayList<>();

  private String latest;
  private String release;

  public InstallerVersions() {
    Document installerManifest = getXml(MANIFEST);

    this.latest =
        installerManifest
            .getElementsByTagName("latest")
            .item(0)
            .getChildNodes()
            .item(0)
            .getNodeValue();

    this.release =
        installerManifest
            .getElementsByTagName("release")
            .item(0)
            .getChildNodes()
            .item(0)
            .getNodeValue();

    this.ALL.clear();

    for (int i = 0; i < installerManifest.getElementsByTagName("version").getLength(); i++) {
      ALL.add(
          installerManifest
              .getElementsByTagName("version")
              .item(i)
              .getChildNodes()
              .item(0)
              .getNodeValue());
    }
  }

  private Document getXml(File manifest) {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = null;
    Document xml = null;

    try {

      documentBuilder = documentBuilderFactory.newDocumentBuilder();

    } catch (ParserConfigurationException ex) {
      System.out.println("Couldn't read document." + ex);
    }

    try {

      assert documentBuilder != null;
      xml = documentBuilder.parse(manifest);

    } catch (IOException | org.xml.sax.SAXException ex) {
      System.out.println("Couldn't read document." + ex);
    }

    assert xml != null;
    xml.normalize();
    return xml;
  }

  public boolean downloadFile(String fileDestination, URL downloadURL) {

    try {
      FileUtils.createParentDirectories(new File(fileDestination));
    } catch (IOException ignored) {

    }

    ReadableByteChannel readableByteChannel = null;
    FileOutputStream fileOutputStream = null;
    FileChannel fileChannel = null;

    try {

      readableByteChannel = Channels.newChannel(downloadURL.openStream());

      fileOutputStream = new FileOutputStream(fileDestination.replace("\\", "/"));

      fileChannel = fileOutputStream.getChannel();

      fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

    } catch (IOException ex) {
      FileUtils.deleteQuietly(new File(fileDestination.replace("\\", "/")));
    } finally {

      try {
        //noinspection ConstantConditions
        fileOutputStream.flush();
      } catch (Exception ignored) {

      }

      try {
        fileOutputStream.close();
      } catch (Exception ignored) {

      }

      try {
        //noinspection ConstantConditions
        readableByteChannel.close();
      } catch (Exception ignored) {

      }

      try {
        //noinspection ConstantConditions
        fileChannel.close();
      } catch (Exception ignored) {

      }
    }

    return new File(fileDestination).exists();
  }


  public List<String> all() {
    return ALL;
  }

  public String latest() {
    return latest;
  }

  public String release() {
    return release;
  }

  public URL latestURL() throws MalformedURLException {
    return new URL(String.format(INSTALLER_URL, latest, latest));
  }

  public URL releaseURL() throws MalformedURLException {
    return new URL(String.format(INSTALLER_URL, release, latest));
  }

  public URL specificURL(String version) throws MalformedURLException {
    if (ALL.contains(version)) {
      return new URL(String.format(INSTALLER_URL, version, version));
    } else {
      throw new IllegalArgumentException("Invalid version specified.");
    }
  }

  public void install(String gameVersion, String loaderVersion) throws IOException {
    List<String> arguments = new ArrayList<>();
    arguments.add("java");
    arguments.add("-jar");
    arguments.add("installer.jar");
    arguments.add("server");
    arguments.add("-mcversion");
    arguments.add(gameVersion);
    arguments.add("-loader");
    arguments.add(loaderVersion);
    arguments.add("-downloadMinecraft");

    ProcessBuilder processBuilder = new ProcessBuilder(arguments).directory(new File("."));
    processBuilder.redirectErrorStream(true);
    Process process = processBuilder.start();

    BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(process.getInputStream()));
    String line;

    while (true) {
      line = bufferedReader.readLine();
      if (line == null) {
        break;
      }
      System.out.println(line);
    }

    try {
      bufferedReader.close();
    } catch (Exception ignored) {

    }

    try {
      process.destroy();
    } catch (Exception ignored) {

    }

    FileUtils.deleteQuietly(new File("installer.jar"));
  }
}
