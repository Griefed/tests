package de.griefed.filebrowser.view;

import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.model.FileNode;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDetailPanel {

    private final Insets INSETS =
            new Insets(6, 6, 6, 6);

    private FileNode fileNode;

    private JLabel fileNameLabel;
    private JLabel fileNameTextLabel;
    private JLabel lastModifiedLabel;
    private JLabel fileSizeLabel;

    private JTextField filePathField;

    private JPanel panel;

    public FileDetailPanel() {
        createPartControl();
    }

    private void createPartControl() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        int gridy = 0;

        fileNameTextLabel = new JLabel(" ");
        addComponent(panel, fileNameTextLabel, 0, gridy,
                INSETS
        );

        fileNameLabel = new JLabel(" ");
        addComponent(panel, fileNameLabel, 1, gridy++,
                INSETS
        );

        JLabel filePathTextLabel = new JLabel("Path:");
        addComponent(panel, filePathTextLabel, 0, gridy,
                INSETS
        );

        filePathField = new JTextField(70);
        filePathField.setEditable(false);
        addComponent(panel, filePathField, 1, gridy++,
                INSETS
        );

        JLabel lastModifiedTextLabel = new JLabel("Last Modified:");
        addComponent(panel, lastModifiedTextLabel, 0, gridy,
                INSETS
        );

        lastModifiedLabel = new JLabel(" ");
        addComponent(panel, lastModifiedLabel, 1, gridy++,
                INSETS
        );

        JLabel fileSizeTextLabel = new JLabel("File Size:");
        addComponent(panel, fileSizeTextLabel, 0, gridy,
                INSETS
        );

        fileSizeLabel = new JLabel(" ");
        addComponent(panel, fileSizeLabel, 1, gridy++,
                INSETS
        );

    }

    private void addComponent(
            Container container,
            Component component,
            int gridx,
            int gridy,
            Insets insets) {

        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
                1, 1, 1.0D, 1.0D, GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                insets, 0, 0);
        container.add(component, gbc);
    }

    private void updatePartControl(FileBrowserModel model) {
        File file = fileNode.getFile();

        if (file.isDirectory()) {
            fileNameTextLabel.setText("Directory:");
        } else {
            fileNameTextLabel.setText("File:");
        }

        fileNameLabel.setText(file.getName());
        fileNameLabel.setIcon(model.getFileIcon(file));
        filePathField.setText(file.getAbsolutePath());
        filePathField.setCaretPosition(0);
        lastModifiedLabel.setText(generateLastModified(file));
        fileSizeLabel.setText(generateFileSize(file));
    }

    private String generateLastModified(File file) {
        long timestamp = file.lastModified();
        Date date = new Date(timestamp);
        String pattern = "EEE, d MMM yyyy zzzz  h:mm:ss aa";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    private String generateFileSize(File file) {
        String[] label = {"bytes", "KB", "GB", "TB"};
        double dbytes = file.length();

        int count = 0;
        while (dbytes > 1000D) {
            dbytes /= 1024D;
            count++;
        }

        return String.format("%.3f ", dbytes) + label[count];
    }

    public void setFileNode(FileNode fileNode, FileBrowserModel model) {
        this.fileNode = fileNode;
        updatePartControl(model);
    }

    public FileNode getFileNode() {
        return fileNode;
    }

    public JPanel getPanel() {
        return panel;
    }

}