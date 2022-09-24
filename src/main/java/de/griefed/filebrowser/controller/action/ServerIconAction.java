package de.griefed.filebrowser.controller.action;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class ServerIconAction extends AbstractAction {

  private File icon = null;

  public ServerIconAction() {
    putValue(Action.NAME,"Set as server-icon");
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {

  }

  public void setIcon(File file) {
    icon = file;
  }
}
