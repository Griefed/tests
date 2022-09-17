package de.griefed.filebrowser.view;

import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.model.FileNode;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileBrowserFrame {

  private final FileBrowserModel MODEL;
  private final JTextField FIELD;
  private DesktopButtonPanel desktopButtonPanel;
  private FileDetailPanel fileDetailPanel;

  private JFrame frame;

  private JPanel mainPanel;

  private TableScrollPane tableScrollPane;

  public FileBrowserFrame(
      FileBrowserModel model,
      JTextField textField) {

    MODEL = model;
    FIELD = textField;
    setLookAndFeel();
    createPartControl();
  }

  private void createPartControl() {
    frame = new JFrame();
    frame.setTitle("File Browser");
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent event) {
        frame.setVisible(false);
      }
    });

    createMainPanel();

    frame.add(mainPanel);
    frame.pack();
    frame.setLocationByPlatform(true);
    frame.setVisible(true);
  }

  private void createMainPanel() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    TreeScrollPane treeScrollPane = new TreeScrollPane(this, MODEL, FIELD);
    mainPanel.add(treeScrollPane.getScrollPane(), BorderLayout.WEST);

    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BorderLayout());

    tableScrollPane = new TableScrollPane(this, MODEL, FIELD);
    rightPanel.add(tableScrollPane.getPanel(),
        BorderLayout.CENTER);

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new BorderLayout());

    fileDetailPanel = new FileDetailPanel();
    southPanel.add(fileDetailPanel.getPanel(), BorderLayout.NORTH);

    desktopButtonPanel = new DesktopButtonPanel(FIELD);
    southPanel.add(desktopButtonPanel.getPanel(),
        BorderLayout.SOUTH);

    rightPanel.add(southPanel, BorderLayout.SOUTH);

    mainPanel.add(rightPanel, BorderLayout.CENTER);
  }

  public void updateFileDetail(FileNode fileNode) {
    fileDetailPanel.setFileNode(fileNode, MODEL);
  }

  public void setDefaultTableModel(DefaultMutableTreeNode node) {
    tableScrollPane.setDefaultTableModel(node);
  }

  public void clearDefaultTableModel() {
    tableScrollPane.clearDefaultTableModel();
  }

  public void setDesktopButtonFileNode(FileNode fileNode) {
    desktopButtonPanel.setFileNode(fileNode);
  }

  private void setLookAndFeel() {
    try {
      // Significantly improves the look of the output in
      // terms of the file names returned by FileSystemView!
      UIManager.setLookAndFeel(
          UIManager.getSystemLookAndFeelClassName());
    } catch (Exception weTried) {
      weTried.printStackTrace();
    }
  }

}