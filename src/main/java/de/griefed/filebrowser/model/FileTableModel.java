package de.griefed.filebrowser.model;

import de.griefed.filebrowser.view.renderer.DateRenderer;
import java.awt.Dimension;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class FileTableModel extends AbstractTableModel {

  private final List<List<Object>> ROWS = new ArrayList<>(10);

  private final String[] COLUMNS = {"Icon", "File", "Size",
      "Last Modified", "Read"};

  public FileTableModel() {
    DateFormat.getDateInstance(DateFormat.DEFAULT);
  }

  @Override
  public int getColumnCount() {
    return COLUMNS.length;
  }

  @Override
  public int getRowCount() {
    return ROWS.size();
  }

  @Override
  public Object getValueAt(int row, int column) {
    return ROWS.get(row).get(column);
  }

  @Override
  public Class<?> getColumnClass(int column) {
    switch (column) {
      case 0:
        return ImageIcon.class;
      case 2:
        return Long.class;
      case 3:
        return Date.class;
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
        return Boolean.class;
      default:
        return String.class;
    }
  }

  @Override
  public String getColumnName(int column) {
    return COLUMNS[column];
  }

  public void addRow(FileBrowserModel model, FileNode fileNode) {
    File file = fileNode.getFile();

    List<Object> list = new ArrayList<>();
    list.add(model.getFileIcon(file));
    list.add(model.getFileText(file));
    list.add(file.length());
    list.add(new Date(file.lastModified()));
    list.add(file.canRead());
    list.add(fileNode);

    this.ROWS.add(list);
  }

  public void removeRows() {
    this.ROWS.clear();
  }

  public int setColumnWidths(JTable table) {
    DefaultTableCellRenderer centerRenderer =
        new DateRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    table.getColumnModel().getColumn(3)
        .setCellRenderer(centerRenderer);

    int width = setColumnWidth(table, 0, 35);
    width += setColumnWidth(table, 1, 450);
    width += setColumnWidth(table, 2, 120);
    width += setColumnWidth(table, 3, 100);
    width += setColumnWidth(table, 4, 0);

    return width + 30;
  }

  private int setColumnWidth(JTable table, int column, int width) {
    TableColumn tableColumn = table.getColumnModel()
        .getColumn(column);
    JLabel label = new JLabel((String) tableColumn.getHeaderValue());
    Dimension preferred = label.getPreferredSize();
    width = Math.max(width, (int) preferred.getWidth() + 14);
    tableColumn.setPreferredWidth(width);
    tableColumn.setMinWidth(width);

    return width;
  }

}
