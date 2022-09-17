package de.griefed.filebrowser.controller.action;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;

public class ModpackDirectoryAction extends AbstractAction {

  protected File directory = null;
  private final JTextField FIELD;

  public ModpackDirectoryAction(JTextField jTextField) {
    FIELD = jTextField;
    putValue(Action.NAME,"Set as modpack directory");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    FIELD.setText(directory.getAbsolutePath());
  }

  public void setDirectory(File file) {
    directory = file;
  }
}
