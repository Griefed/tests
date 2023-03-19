package de.griefed.filebrowser.controller.action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class OpenAction extends AbstractAction {

    private File file;

    public OpenAction() {
        putValue(Action.NAME, "Open");
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                try {
                    desktop.open(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void setFile(File file) {
        this.file = file;
    }
}
