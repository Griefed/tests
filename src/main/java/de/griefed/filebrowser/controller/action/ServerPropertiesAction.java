package de.griefed.filebrowser.controller.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class ServerPropertiesAction extends AbstractAction {

  public ServerPropertiesAction() {
    putValue(Action.NAME,"Set as server.properties");
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
