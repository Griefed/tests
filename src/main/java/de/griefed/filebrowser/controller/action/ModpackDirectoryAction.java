package de.griefed.filebrowser.controller.action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ModpackDirectoryAction extends AbstractAction {

    private final JTextField FIELD;
    private File directory = null;

    public ModpackDirectoryAction(JTextField jTextField) {
        FIELD = jTextField;
        putValue(Action.NAME, "Set as modpack directory");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FIELD.setText(directory.getAbsolutePath());
    }

    public void setDirectory(File file) {
        directory = file;
    }
}
