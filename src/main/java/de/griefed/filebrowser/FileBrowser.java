package de.griefed.filebrowser;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.view.FileBrowserFrame;

import javax.swing.*;

public class FileBrowser implements Runnable {

    private final JTextField FIELD;
    private final FileBrowserModel MODEL;

    public FileBrowser(JTextField textField) {
        setLookAndFeel();
        MODEL = new FileBrowserModel();
        FIELD = textField;
    }

    public void reload() {
        MODEL.reload();
    }

    @Override
    public void run() {
        new FileBrowserFrame(MODEL, FIELD);
    }

    private void setLookAndFeel() {
        try {
            FlatDarkPurpleIJTheme.setup();
        } catch (Exception weTried) {
            weTried.printStackTrace();
        }
    }
}
