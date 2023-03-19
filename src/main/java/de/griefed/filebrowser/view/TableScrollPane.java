package de.griefed.filebrowser.view;

import de.griefed.filebrowser.controller.TableMouseListener;
import de.griefed.filebrowser.controller.TableSelectionListener;
import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.model.FileNode;
import de.griefed.filebrowser.model.FileTableModel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;
import java.util.Enumeration;

public class TableScrollPane {

    private final FileBrowserFrame FRAME;

    private final FileBrowserModel MODEL;
    private final JTextField FIELD;

    private FileTableModel ftModel;

    private JLabel countLabel;

    private JPanel panel;

    private JScrollPane scrollPane;

    private TableSelectionListener tsListener;

    public TableScrollPane(
            FileBrowserFrame frame,
            FileBrowserModel model,
            JTextField textField) {
        FRAME = frame;
        MODEL = model;
        FIELD = textField;
        createPartControl();
    }

    private void createPartControl() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel countPanel = new JPanel();

        countLabel = new JLabel(" ");

        countPanel.add(countLabel);
        panel.add(countPanel, BorderLayout.NORTH);

        ftModel = new FileTableModel();

        JTable table = new JTable(ftModel);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setColumnSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new TableMouseListener(table, FIELD));

        tsListener = new TableSelectionListener(FRAME, table);
        tsListener.setRowCount(ftModel.getRowCount());

        ListSelectionModel lsm = table.getSelectionModel();
        lsm.addListSelectionListener(tsListener);

        int width = ftModel.setColumnWidths(table);
        table.setPreferredScrollableViewportSize(
                new Dimension(width, table.getRowHeight() * 12));

        scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public String buildLabelString(int count) {
        NumberFormat nf = NumberFormat.getInstance();
        return nf.format(count)
                + " files / directories";
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void clearDefaultTableModel() {
        ftModel.removeRows();
        countLabel.setText(" ");
        ftModel.fireTableDataChanged();
    }

    public void setDefaultTableModel(DefaultMutableTreeNode node) {
        ftModel.removeRows();

        FileNode fileNode = (FileNode) node.getUserObject();
        File file = fileNode.getFile();
        if (file.isDirectory()) {
            Enumeration<?> enumeration = node.children();
            while (enumeration.hasMoreElements()) {
                DefaultMutableTreeNode childNode =
                        (DefaultMutableTreeNode)
                                enumeration.nextElement();
                FileNode childFileNode =
                        (FileNode) childNode.getUserObject();
                ftModel.addRow(MODEL, childFileNode);
            }
        }

        tsListener.setRowCount(ftModel.getRowCount());
        countLabel.setText(buildLabelString(ftModel.getRowCount()));
        ftModel.fireTableDataChanged();
        scrollPane.getVerticalScrollBar().setValue(0);
    }

}