package de.griefed.filebrowser.view;

import com.formdev.flatlaf.extras.components.FlatSplitPane;
import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.model.FileNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FileBrowserFrame {

    private final FileBrowserModel MODEL;
    private final JTextField FIELD;
    private DesktopButtonPanel desktopButtonPanel;
    private FileDetailPanel fileDetailPanel;

    private JFrame frame;

    private JSplitPane splitPane;

    private TableScrollPane tableScrollPane;

    public FileBrowserFrame(
            FileBrowserModel model,
            JTextField textField) {

        MODEL = model;
        FIELD = textField;
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

        //frame.add(mainPanel);
        frame.add(splitPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private void createMainPanel() {
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

        TreeScrollPane treeScrollPane = new TreeScrollPane(this, MODEL, FIELD);
        splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                treeScrollPane.getScrollPane(),
                rightPanel
        );
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(300);
        splitPane.setDividerSize(20);
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
}