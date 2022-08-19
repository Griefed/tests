package de.griefed;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;

public class GameVersions extends Meta {

  public GameVersions(URL endpoint, ObjectMapper mapper)
      throws IOException {
    super(endpoint, mapper);
  }
}
