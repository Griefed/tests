package de.griefed;

import de.griefed.filebrowser.FileBrowser;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JTextField textField1 = new JTextField(
                "C:\\Minecraft\\Game\\Instances\\Survive Create Prosper 4");
        SwingUtilities.invokeLater(new FileBrowser(textField1));
    }
}
