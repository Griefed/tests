package de.griefed.filebrowser.controller.action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ServerIconAction extends AbstractAction {

    private File icon = null;

    public ServerIconAction() {
        putValue(Action.NAME, "Set as server-icon");
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
