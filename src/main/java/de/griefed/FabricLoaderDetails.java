package de.griefed;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class FabricLoaderDetails {

  private final ObjectMapper OBJECT_MAPPER;

  public FabricLoaderDetails() {
    OBJECT_MAPPER = new ObjectMapper();
    OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
  }

  public Details getDetails(String minecraftVersion, String modloaderVersion) throws IOException {
    return OBJECT_MAPPER.readValue(
        new URL(
            "https://meta.fabricmc.net/v2/versions/loader/"
                + minecraftVersion
                + "/"
                + modloaderVersion
                + "/server/json"),
        Details.class);
  }

  public static class Details {

    private String id;
    private String inheritsFrom;
    private Date releaseTime;
    private Date time;
    private String type;
    private String mainClass;
    private Arguments arguments;
    private List<Library> libraries;

    private Details() {}

    public Details(String id, String inheritsFrom, Date releaseTime, Date time, String type,
        String mainClass, Arguments arguments, List<Library> libraries) {
      this.id = id;
      this.inheritsFrom = inheritsFrom;
      this.releaseTime = releaseTime;
      this.time = time;
      this.type = type;
      this.mainClass = mainClass;
      this.arguments = arguments;
      this.libraries = libraries;
    }

    public String getId() {
      return id;
    }

    public String getInheritsFrom() {
      return inheritsFrom;
    }

    public Date getReleaseTime() {
      return releaseTime;
    }

    public Date getTime() {
      return time;
    }

    public String getType() {
      return type;
    }

    public String getMainClass() {
      return mainClass;
    }

    public Arguments getArguments() {
      return arguments;
    }

    public List<Library> getLibraries() {
      return libraries;
    }
  }

  public static class Library {
    private String name;
    private String url;

    private Library() {}

    public String getName() {
      return name;
    }

    public String getUrl() {
      return url;
    }
  }

  public static class Arguments {
    private Object[] game;

    private Arguments() {}

    public Object[] getGame() {
      return game;
    }
  }
}
