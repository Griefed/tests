package de.griefed.filebrowser.view;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.components.FlatButton;
import de.griefed.filebrowser.model.FileNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DesktopButtonPanel {

    private FileNode fileNode;

    private JPanel panel;

    public DesktopButtonPanel(JTextField textField) {
        createPartControl(textField);
    }

    private void createPartControl(JTextField textField) {
        panel = new JPanel();
        panel.add(textField);

        FlatButton themeButton = new FlatButton();
        themeButton.setText("Switch Theme");
        themeButton.addActionListener(new ThemeListener());
        panel.add(themeButton);
    }

    public void setFileNode(FileNode fileNode) {
        this.fileNode = fileNode;
    }

    public JPanel getPanel() {
        return panel;
    }

    public static class ThemeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                FlatAnimatedLafChange.showSnapshot();
                UIManager.setLookAndFeel(new FlatDarculaLaf());
                FlatDarculaLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}