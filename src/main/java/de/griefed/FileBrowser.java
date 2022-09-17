package de.griefed;

import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.view.FileBrowserFrame;
import javax.swing.JTextField;

public class FileBrowser implements Runnable {

  private final FileBrowserModel MODEL;
  private final JTextField FIELD;

  public FileBrowser(FileBrowserModel model,JTextField textField) {
    MODEL = model;
    FIELD = textField;
  }

  @Override
  public void run() {
    new FileBrowserFrame(MODEL,FIELD);
  }

}
