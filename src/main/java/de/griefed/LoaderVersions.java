package de.griefed;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;

public class LoaderVersions extends Meta {

  public LoaderVersions(URL endpoint, ObjectMapper mapper)
      throws IOException {
    super(endpoint, mapper);
  }
}
