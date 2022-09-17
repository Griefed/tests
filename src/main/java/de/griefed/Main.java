package de.griefed;

import de.griefed.filebrowser.model.FileBrowserModel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {

  public static void main(String[] args) {
    FileBrowserModel model = new FileBrowserModel();

    JTextField textField1 = new JTextField("oneoneone");
    SwingUtilities.invokeLater(new FileBrowser(model,textField1));

    JTextField textField2 = new JTextField("twotwotwo");
    SwingUtilities.invokeLater(new FileBrowser(model,textField2));
  }
}
