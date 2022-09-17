package de.griefed.filebrowser.view;

import de.griefed.filebrowser.model.FileNode;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DesktopButtonPanel {

  private FileNode fileNode;

  private JPanel panel;

  public DesktopButtonPanel(JTextField textField) {
    createPartControl(textField);
  }

  private void createPartControl(JTextField textField) {
    panel = new JPanel();
    panel.add(textField);

    //JButton openButton = new JButton("Open");
    //openButton.addActionListener(new OpenListener());
  }

  public void setFileNode(FileNode fileNode) {
    this.fileNode = fileNode;
  }

  public JPanel getPanel() {
    return panel;
  }

  /*public class OpenListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      openFile(fileNode);
    }

    private void openFile(FileNode fileNode) {
      if (fileNode.getFile().isFile()) {
        if (Desktop.isDesktopSupported()) {
          Desktop desktop = Desktop.getDesktop();
          if (desktop.isSupported(Desktop.Action.OPEN)) {
            try {
              desktop.open(fileNode.getFile());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }

  }*/
}